package com.authentication.auth.service;

import com.authentication.auth.model.User;
import com.authentication.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Optional<User> findUserByEmail(String email)
    {
        Optional<User> usr=userRepository.findByEmail(email);
        return usr;
    }

    public String addUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User saved successfully";
    }

}
