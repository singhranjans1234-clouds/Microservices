package com.tek.TEK_UserService.service.impl;

import com.tek.TEK_UserService.entity.User;
import com.tek.TEK_UserService.repository.UserRepository;
import com.tek.TEK_UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user) ;
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

}
