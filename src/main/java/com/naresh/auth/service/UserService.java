package com.naresh.auth.service;

import com.naresh.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
