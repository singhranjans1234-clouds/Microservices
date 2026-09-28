package com.tek.TEK_UserService.controller;

import com.tek.TEK_UserService.dto.UserRequestDTO;
import com.tek.TEK_UserService.dto.UserResponseDTO;
import com.tek.TEK_UserService.entity.User;
import com.tek.TEK_UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());

        User savedUser = userService.save(user);

        return new ResponseEntity<>(toResponseDTO(savedUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(user -> ResponseEntity.ok(toResponseDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }
}
