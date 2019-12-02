package com.naresh.auth.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.naresh.auth.exception.FileStorageException;
import com.naresh.auth.exception.MyFileNotFoundException;
import com.naresh.auth.model.ProfileImages;
import com.naresh.auth.model.User;
import com.naresh.auth.repository.ProfileImagesRepository;

@Component
public class ProfileImagesStorageService {

	  @Autowired
	    private ProfileImagesRepository profileImageRepository;
	  
	  @Autowired
		private UserService userService;
	  

		@Autowired 
		private UserServiceImpl userServiceImpl;

	    public ProfileImages storeFile(MultipartFile file) {
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }
	            
	            ProfileImages ProfileImages = new ProfileImages(fileName, file.getContentType(), file.getBytes());
	        	Optional<User> user=userService.findByUsername(getPrincipal());
	    		//System.out.println("Hi:"+user.getProfileImages());
	    		
	        	if(user.isPresent())
	            ProfileImages.setUser(user.get());

	            return profileImageRepository.save(ProfileImages);
	        } catch (IOException ex) {
	            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
	        }
	    }

	    public ProfileImages getFile(String fileId) {
	        return profileImageRepository.findById(fileId)
	                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	    }
	    
	    public void deleteProfileImage(String fileId) {
	    	profileImageRepository.deleteById(fileId);
	    }
	    
	    public List<ProfileImages> getProfileImages() {
	    	Optional<User> user=userService.findByUsername(getPrincipal());
	       /* return profileImageRepository.findByUser(user)
	                .orElseThrow(() -> new Exception("Images not found with user id "));*/
	    	return profileImageRepository.findByUser(user.get());
	    }
	    
	    private String getPrincipal() {
			String userName = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}
			return userName;
		}
	

}
