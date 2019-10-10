package com.naresh.auth.service;

import com.naresh.auth.model.UserAttempts;

public interface UserAttemptsService {

	void updateFailAttempts(String username);
	void resetFailAttempts(String username);
	UserAttempts findUserAttempts(String username);
	void save(String username);
}
