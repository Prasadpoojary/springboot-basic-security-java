package com.authentication.auth.controller;


import com.authentication.auth.model.User;
import com.authentication.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    public AuthService authService;
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello()
    {
        String message="Hello world...";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user)
    {
        String response= authService.addUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/secure")
    public ResponseEntity<String> secureTest()
    {
        String message="Secured text";
        return ResponseEntity.ok(message);
    }

}
