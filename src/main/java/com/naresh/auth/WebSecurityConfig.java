package com.naresh.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.naresh.auth.handler.SimpleAuthenticationSuccessHandler;
import com.naresh.auth.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
	@Qualifier("authenticationProvider")
	AuthenticationProvider authenticationProvider;
    
    @Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

  /*  @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors().and()
            .authorizeRequests()
                .antMatchers("/resources/**", "/registration","/forgotPassword","/forgotPassword1","/reset").permitAll()
                .antMatchers("/profile","/index2").hasRole("USER")
				.antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .successHandler(successHandler)
                .loginPage("/login")
                .permitAll()
                .and()
            .logout().deleteCookies("JSESSIONID")
                .permitAll()
                		.and()
	                		.rememberMe()
	        				.key("profile")
	        				.rememberMeParameter("remember-me")
	        				.rememberMeCookieName("profile-login-remember-me")
	        				.tokenValiditySeconds(10000000)
                        .and()
                		.csrf().disable();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
        userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder())
        .and()
        .authenticationProvider(authenticationProvider);
    }
}