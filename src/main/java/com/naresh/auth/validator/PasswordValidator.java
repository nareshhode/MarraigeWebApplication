package com.naresh.auth.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	 
    private Pattern pattern;
    private Matcher matcher;
 
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,40}$";
 
   /* public PasswordValidator(String PASSWORD_PATTERN) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }*/
 
    public boolean validate(final String password) {
 
    	pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
 
    }
}
