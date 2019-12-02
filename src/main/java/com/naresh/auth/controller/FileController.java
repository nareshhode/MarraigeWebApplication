package com.naresh.auth.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.naresh.auth.model.ProfileImages;
import com.naresh.auth.payload.UploadFileResponse;
import com.naresh.auth.service.ProfileImagesStorageService;

@Controller
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private ProfileImagesStorageService profileImagesStorageService;
	

	@PostMapping("/uploadFile")
public String uploadFile(@RequestParam("file") MultipartFile file) {
		
		ProfileImages ProfileImages = profileImagesStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(ProfileImages.getId()).toUriString();

		/*return new UploadFileResponse(ProfileImages.getFileName(), fileDownloadUri, file.getContentType(),
				file.getSize());*/

		  
		return "redirect:/uploadImage";
	}
/*
	@PostMapping("/uploadMultipleFiles")
	public List<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}*/

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		// Load file from database
		ProfileImages ProfileImages = profileImagesStorageService.getFile(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(ProfileImages.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ProfileImages.getFileName() + "\"")
				.body(new ByteArrayResource(ProfileImages.getData()));
	}
	
	@GetMapping("/getImages/{profileImage}")
	public void getImages(HttpServletResponse response, @PathVariable("profileImage") String profImagId) throws Exception {

		response.setContentType("image/jpeg");
		
		ProfileImages profImag=profileImagesStorageService.getFile(profImagId) ;

		byte[] bytes = profImag.getData();
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	@PostMapping("/delete/{profileImage}")
	public String deleteImage(@PathVariable("profileImage") String profImagId) throws Exception {

		profileImagesStorageService.deleteProfileImage(profImagId);
		return "redirect:/uploadImage";

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
