package com.naresh.auth.validator;

import com.naresh.auth.model.User;
import com.naresh.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        /*if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        */
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        
        if(!user.getUsername().matches(regex)){
        	errors.rejectValue("username", "Invalid.userForm.username");
        }
      

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        
        //check password contains atleast one lowercase character
        PasswordValidator lowerCasePasswordValidator=new PasswordValidator("^(?=.*[a-z])");
        if (!lowerCasePasswordValidator.validate(user.getPassword())) {
            errors.rejectValue("password", "Lowercase.userForm.password");
        }
       
        //check password contains atleast one uppercase character
        PasswordValidator upperCasePasswordValidator=new PasswordValidator("^(?=.*[A-Z])");
        if (!upperCasePasswordValidator.validate(user.getPassword())) {
            errors.rejectValue("password", "Uppercase.userForm.password");
        }
        
        //check password contains atlest one digit
        PasswordValidator digitPasswordValidator=new PasswordValidator("^(?=.*\\d)");
        if (!digitPasswordValidator.validate(user.getPassword())) {
            errors.rejectValue("password", "Digit.userForm.password");
        }
        
        //check password contains atlest one special character "@#$%"
        PasswordValidator specialCharacterPasswordValidator=new PasswordValidator("^(?=.*[@#$%])");
        if (!specialCharacterPasswordValidator.validate(user.getPassword())) {
            errors.rejectValue("password", "SpecialCharacter.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        
        
    }
    
    public static boolean isNullOrWhitespace(CharSequence value) {
        return value == null || value.codePoints().allMatch(c -> Character.isWhitespace(c));
    }
}
