package com.naresh.auth.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naresh.auth.model.ProfileImages;
import com.naresh.auth.model.User;
import com.naresh.auth.service.ProfileImagesStorageService;
import com.naresh.auth.service.SecurityService;
import com.naresh.auth.service.UserAttemptsService;
import com.naresh.auth.service.UserService;
import com.naresh.auth.validator.UserValidator;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private ProfileImagesStorageService profileImagesStorageService;
	
	@Autowired
	private UserAttemptsService userAttemptService;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "login";
		}

		userService.save(userForm);
		
		userAttemptService.save(userForm.getUsername());

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model,  @RequestParam(value = "error", required = false) String error, String logout, HttpServletRequest request) {
		if (error != null)
			model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		model.addAttribute("userForm", new User());
		return "login";
	}
	
    @GetMapping("/admin1")
    public String admin(Model model) {
    	
    	return "admin1";
    }
    
    @GetMapping("/profile1")
    public String profile() {
    	return "createProfile";
    	
    }
    
    @GetMapping("/uploadImage")
    public ModelAndView index2(ModelAndView model) {
    	List<ProfileImages> listProfileImages = profileImagesStorageService.getProfileImages();

		model.addObject("listProfileImages", listProfileImages);
		model.setViewName("uploadImage");
    	return model;
    	
    }

	@GetMapping({ "/", "/welcome" })
	public String welcome(Model model) {
		//Optionall<User> user=userService.findByUsername("naresh@gmail.com");
		//System.out.println("Hi:"+user.getProfileImages());
		return "welcome";
	}

	@GetMapping(value = "/Access_Denied")
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
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

	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}
}
