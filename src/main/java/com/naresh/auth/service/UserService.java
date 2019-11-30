package com.naresh.auth.service;

import java.util.Optional;

import com.naresh.auth.model.User;

public interface UserService {
    void save(User user);

    Optional<User> findByUsername(String username);
    
    
    Optional<User> findByResettoken(String resetToken);
    
    
}
