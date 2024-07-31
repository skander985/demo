package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.JwtAuthenticationResponse;
import com.example.demo.service.AdministrateurService;
import com.example.demo.service.EmployeService;
import com.example.demo.service.RHService;
import com.example.demo.entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AdministrateurService administrateurService;

    @Autowired
    private EmployeService employeService;

    @Autowired
    private RHService rhService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestParam String email, @RequestParam String motDePasse) {
        boolean loginSuccessful = false;

        if (administrateurService.existsByEmail(email)) {
            loginSuccessful = administrateurService.login(email, motDePasse);
        } else if (employeService.existsByEmail(email)) {
            loginSuccessful = employeService.login(email, motDePasse);
        } else if (rhService.existsByEmail(email)) {
            loginSuccessful = rhService.login(email, motDePasse);
        }

        if (loginSuccessful) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            String jwt = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null));
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } else {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String role, @RequestBody User user) {
        User registeredUser = null;
        switch (role) {
            case "ADMIN":
                registeredUser = administrateurService.register(user);
                break;
            case "EMPLOYEE":
                registeredUser = employeService.register(user);
                break;
            case "RH":
                registeredUser = rhService.register(user);
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}

