package com.naresh.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naresh.auth.model.UserAttempts;

public interface UserAttemptsRepository extends JpaRepository<UserAttempts, Long> {

	
	UserAttempts findUserAttemptsByusernameIgnoreCase(String username);
	
	
	

}
