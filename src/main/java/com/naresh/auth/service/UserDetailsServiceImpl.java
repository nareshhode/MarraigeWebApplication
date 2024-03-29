package com.naresh.auth.service;

import com.naresh.auth.model.Role;
import com.naresh.auth.model.User;
import com.naresh.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
    	Set<GrantedAuthority> grantedAuthorities=null;
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
        	throw new UsernameNotFoundException(username);
		} else {
       

        grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : user.get().getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
		}

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), grantedAuthorities);
    }
    
   
}
