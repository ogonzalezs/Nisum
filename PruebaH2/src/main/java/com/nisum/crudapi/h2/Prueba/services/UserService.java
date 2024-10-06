package com.nisum.crudapi.h2.Prueba.services;

import com.nisum.crudapi.h2.Prueba.dto.UserRequest;
import com.nisum.crudapi.h2.Prueba.dto.UserResponse;
import com.nisum.crudapi.h2.Prueba.entities.Phone;
import com.nisum.crudapi.h2.Prueba.entities.User;
import com.nisum.crudapi.h2.Prueba.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Value("${email.validation.regex}")
    private String emailRegex;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        return userRepository.findById(id).map(this::convertToResponse).orElse(null);
    }

    public UserResponse createUser(UserRequest userRequest) {
        validateEmail(userRequest.getEmail());

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
       /** user.setPhones(userRequest.getPhones().stream().map(phoneRequest -> convertToEntityPhone(phoneRequest, user)).collect(Collectors.toList()));**/
       Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
       if (optionalUser.isPresent()){
           throw new RuntimeException("Usuario ya registrado con el email: " + userRequest.getEmail()); // Manejar excepción adecuadamente
       }else{
           userRepository.save(user);
       }
        return convertToResponse(user);
    }

    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        return userRepository.findById(id).map(user -> {
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            userRepository.save(user);
            return convertToResponse(user);
        }).orElse(null);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public UserResponse getUserByEmail(UserRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return convertToUserResponse(user);
        } else {
            throw new RuntimeException("Usuario no encontrado con el email: " + request.getEmail()); // Manejar excepción adecuadamente
        }
    }

    private UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setLastLogin(user.getUpdatedAt());
        response.setActive(user.isActive());

        return response;
    }
    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setLastLogin(user.getUpdatedAt());
        response.setActive(user.isActive());
        return response;
    }

    private Phone convertToEntityPhone(UserRequest.PhoneRequest phoneRequest, User user) {
        Phone phone = new Phone();
        phone.setNumber(phoneRequest.getNumber());
        phone.setCitycode(phoneRequest.getCitycode());
        phone.setCountrycode(phoneRequest.getContrycode());

        return phone;
    }

    private void validateEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }
    }
}