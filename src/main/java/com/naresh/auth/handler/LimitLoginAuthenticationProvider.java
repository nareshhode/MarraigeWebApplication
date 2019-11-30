package com.naresh.auth.handler;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.naresh.auth.model.UserAttempts;
import com.naresh.auth.service.UserAttemptsService;
import com.naresh.auth.service.UserDetailsServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	public LimitLoginAuthenticationProvider() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	UserAttemptsService userAttemptsService;
	
	  @Autowired
	  private UserDetailsService userDetailsService;

	
	  @Autowired
	//  @Qualifier("userDetailsService")
	  @Override public void setUserDetailsService(UserDetailsService userDetailsService) 
	  { super.setUserDetailsService(userDetailsService); }
	 

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);

			// if reach here, means login success, else an exception will be thrown
			// reset the user_attempts
			userAttemptsService.resetFailAttempts(authentication.getName());

			return auth;

		} catch (BadCredentialsException e) {

			// invalid login, update to user_attempts
			userAttemptsService.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {

			// this user is locked!
			String error = "";
			UserAttempts userAttempts = userAttemptsService.findUserAttempts(authentication.getName());

			if (userAttempts != null) {
				Timestamp lastAttempts = userAttempts.getLastModified();
				error = "User account is locked! <br><br>Username : " + authentication.getName()
						+ "<br>Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}

			throw new LockedException(error);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
