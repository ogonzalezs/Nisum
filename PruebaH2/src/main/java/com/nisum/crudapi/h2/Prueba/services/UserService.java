package com.nisum.crudapi.h2.Prueba.services;

import com.nisum.crudapi.h2.Prueba.dto.UserRequest;
import com.nisum.crudapi.h2.Prueba.dto.UserResponse;
import com.nisum.crudapi.h2.Prueba.entities.Phone;
import com.nisum.crudapi.h2.Prueba.entities.User;
import com.nisum.crudapi.h2.Prueba.repositories.PhoneRepository;
import com.nisum.crudapi.h2.Prueba.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @Value("${email.validation.regex}")
    private String emailRegex;

    @Value("${password.validation.regex}")
    private String passwordRegex;

    public UserService(UserRepository userRepository, PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        return userRepository.findById(id).map(this::convertToResponse).orElse(null);
    }

    public UserResponse createUser(UserRequest userRequest) {
        validateEmail(userRequest.getEmail());
        validatePassword(userRequest.getPassword());

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        if (user.getPhones() == null) {
            user.setPhones(new ArrayList<>());
        }

        userRequest.getPhones().forEach(phoneRequest -> {
            Phone phone = convertToEntityPhone(phoneRequest, user);
            user.getPhones().add(phone); // Agregar a la colección existente
        });
       Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());

       if (optionalUser.isPresent()){
           throw new IllegalArgumentException("Usuario ya registrado con el email: " + userRequest.getEmail()); // Manejar excepción adecuadamente
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
            return convertToResponse(user);
        } else {
            throw new RuntimeException("Usuario no encontrado con el email: " + request.getEmail()); // Manejar excepción adecuadamente
        }
    }


    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setLastLogin(user.getUpdatedAt());
        response.setActive(user.isActive());
        return response;
    }

    private Phone convertToEntityPhone(UserRequest.PhoneRequest phoneRequest, User user) {
        Phone phone = new Phone();
        phone.setNumber(phoneRequest.getNumber());
        phone.setCitycode(phoneRequest.getCitycode());
        phone.setCountrycode(phoneRequest.getContrycode());

        phone.setUser(user);
        return phone;
    }

    // Region : Validadores Mail y Password
    private void validateEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }
    }

    private void validatePassword(String password) {
        Pattern pattern = Pattern.compile(passwordRegex);
        if (!pattern.matcher(password).matches()) {
            throw new IllegalArgumentException("La contraseña no cumple con el formato requerido. Debe tener al menos: Una letra mayuscula, una letra minuscula, un numero, un caracter especial y mas de 8 digitos de longitud.");
        }
    }
}