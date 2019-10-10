package com.naresh.auth.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;

import com.naresh.auth.model.Role;
import com.naresh.auth.model.User;
import com.naresh.auth.model.UserAttempts;
import com.naresh.auth.repository.UserAttemptsRepository;
import com.naresh.auth.repository.UserRepository;
import com.naresh.auth.util.DateTimeUtil;
@Service
public class UserAttemptsServiceImpl implements UserAttemptsService {
	
	private static final int MAX_ATTEMPTS = 3;


	public UserAttemptsServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
    private UserService userService;
	
	@Autowired
    private UserAttemptsRepository attemptsRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public void updateFailAttempts(String username) {

	  UserAttempts user = findUserAttempts(username);
	  if (user == null) {
		if (!isUserExists(username)) {
			// if no record, insert a new
			UserAttempts userAttempts=new UserAttempts();
			userAttempts.setUsername(username);
			userAttempts.setAttempts(1);
			userAttempts.setLastModified(DateTimeUtil.getCurrentTimeStamp());
			attemptsRepository.save(userAttempts);
		}
	  } else {

		if (isUserExists(username)) {
			// update attempts count, +1
			UserAttempts userAttempts=findUserAttempts(username);
			userAttempts.setAttempts(userAttempts.getAttempts()+1);
			userAttempts.setLastModified(DateTimeUtil.getCurrentTimeStamp());
			attemptsRepository.save(userAttempts);
		}

		if (user.getAttempts() + 1 >= MAX_ATTEMPTS) {
			
	        User user2=userService.findByUsername(username);
	        user2.setAccountNonLocked(false);
	        userRepository.save(user2);
			
			// throw exception
			throw new LockedException("User Account is locked!");
		}

	  }

	}

	@Override
	public UserAttempts findUserAttempts(String username) {

	  try {

		UserAttempts userAttempts =attemptsRepository.findUserAttemptsByusernameIgnoreCase(username); 
				return userAttempts;

	  } catch (EmptyResultDataAccessException e) {
		return null;
	  }

	}

	@Override
	public void resetFailAttempts(String username) {
		
		UserAttempts userAttempts=findUserAttempts(username);
		userAttempts.setAttempts(0);
		userAttempts.setLastModified(DateTimeUtil.getCurrentTimeStamp());
		attemptsRepository.save(userAttempts);
		
	}
	
	public boolean isUserExists(String username) {
		
		boolean isUserExist=false;
		
		if(userService.findByUsername(username) != null){
			isUserExist=true;
		}
		return isUserExist;
	}
	
	 public void save(String userName) {
	     	UserAttempts userAttempts=new UserAttempts();
			userAttempts.setUsername(userName);
			userAttempts.setAttempts(0);
			
	        attemptsRepository.save(userAttempts);
	 }
	


}
