package com.authentication.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig
{
    @Bean
    public UserDetailsService userDetailsService()
    {
      return new UserInfoDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         return http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/auth/hello","/auth/add").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/secure").authenticated()
                .and().build();
    }

    @Bean
     public AuthenticationProvider authenticationProvider()
     {
         DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
         authProvider.setUserDetailsService(userDetailsService());
         authProvider.setPasswordEncoder(passwordEncoder());
         return authProvider;
     }

}
