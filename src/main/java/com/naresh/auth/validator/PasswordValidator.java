package com.naresh.auth.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	 
    private Pattern pattern;
    private Matcher matcher;
 
    //private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
 
    public PasswordValidator(String pwdPattern) {
        pattern = Pattern.compile(pwdPattern);
    }
 
    public boolean validate(final String password) {
 
        matcher = pattern.matcher(password);
        return matcher.matches();
 
    }
}
