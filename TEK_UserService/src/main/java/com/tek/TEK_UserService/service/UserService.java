package com.tek.TEK_UserService.service;

import com.tek.TEK_UserService.entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);


    Optional<User> getUser(Long id);
}
