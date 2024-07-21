package com.example.demo.controller;

import com.example.demo.entity.Administrateur;
import com.example.demo.entity.User;
import com.example.demo.security.JwtAuthenticationResponse;
import com.example.demo.service.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;

import java.util.List;

@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AdministrateurService administrateurService;

    @GetMapping
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        List<Administrateur> administrateurs = administrateurService.findAll();
        return new ResponseEntity<>(administrateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable("id") int id) {
        return administrateurService.findById(id)
                .map(administrateur -> new ResponseEntity<>(administrateur, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) {
        Administrateur createdAdministrateur = administrateurService.save(administrateur);
        return new ResponseEntity<>(createdAdministrateur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable("id") int id, @RequestBody Administrateur administrateur) {
        administrateur.setId(id); // Ensure the ID is set from the path variable
        try {
            Administrateur updatedAdministrateur = administrateurService.update(administrateur);
            return new ResponseEntity<>(updatedAdministrateur, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable("id") int id) {
        administrateurService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestParam String email, @RequestParam String motDePasse) {
        boolean loginSuccessful = administrateurService.login(email, motDePasse);
        if (loginSuccessful) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            String jwt = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null));
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = administrateurService.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}

