package com.authentication.auth.config;

import com.authentication.auth.model.User;
import com.authentication.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoDetailService implements UserDetailsService
{
    @Autowired
    AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user=authService.findUserByEmail(email);
        return user.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
    }
}
