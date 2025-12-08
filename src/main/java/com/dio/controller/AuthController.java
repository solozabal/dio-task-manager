package com.dio.controller;

import com.dio.model.User;
import com.dio.service.CustomUserDetailsService;
import com.dio.service.JwtService;
import com.dio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> authRequest) {
        String username = authRequest.get("username");
        String password = authRequest.get("password");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(Map.of(
                "message", "Usuário registrado com sucesso",
                "username", registeredUser.getUsername()
        ));
    }
}