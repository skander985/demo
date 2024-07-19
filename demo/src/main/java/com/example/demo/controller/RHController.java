package com.example.demo.controller;

import com.example.demo.entity.RH;
import com.example.demo.entity.User;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.RHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.demo.security.JwtAuthenticationResponse;


import java.util.List;

@RestController
@RequestMapping("/api/rhs")
public class RHController {

    @Autowired
    private RHService rhService;

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<List<RH>> getAllRHs() {
        List<RH> rhs = rhService.findAll();
        return new ResponseEntity<>(rhs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RH> getRHById(@PathVariable("id") int id) {
        return rhService.findById(id)
                .map(rh -> new ResponseEntity<>(rh, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RH> createRH(@RequestBody RH rh) {
        RH createdRH = rhService.save(rh);
        return new ResponseEntity<>(createdRH, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RH> updateRH(@PathVariable("id") int id, @RequestBody RH rh) {
        rh.setId(id); // Ensure the ID is set from the path variable
        try {
            RH updatedRH = rhService.update(rh);
            return new ResponseEntity<>(updatedRH, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRH(@PathVariable("id") int id) {
        rhService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String motDePasse) {
        boolean loginSuccessful = rhService.login(email, motDePasse);
        if (loginSuccessful) {


            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            System.out.println("hello ");

            String jwt = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null));

            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User rh) {
        User registeredRH = rhService.register(rh);
        return new ResponseEntity<>(registeredRH, HttpStatus.CREATED);
    }
}

