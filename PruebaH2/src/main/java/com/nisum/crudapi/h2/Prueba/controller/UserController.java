package com.nisum.crudapi.h2.Prueba.controller;

import com.nisum.crudapi.h2.Prueba.dto.UserRequest;
import com.nisum.crudapi.h2.Prueba.dto.UserResponse;
import com.nisum.crudapi.h2.Prueba.services.UserService;
import com.nisum.crudapi.h2.Prueba.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }


    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }


    @GetMapping("/email")
    public UserResponse getUserByEmail(@RequestBody UserRequest request) {
        return userService.getUserByEmail(request);

    }
    
}
