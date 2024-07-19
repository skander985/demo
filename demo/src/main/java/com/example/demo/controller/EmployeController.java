package com.example.demo.controller;

import com.example.demo.entity.Employe;
import com.example.demo.entity.User;
import com.example.demo.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @GetMapping
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = employeService.findAll();
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable("id") int id) {
        return employeService.findById(id)
                .map(employe -> new ResponseEntity<>(employe, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) {
        Employe createdEmploye = employeService.save(employe);
        return new ResponseEntity<>(createdEmploye, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable("id") int id, @RequestBody Employe employe) {
        employe.setId(id); // Ensure the ID is set from the path variable
        try {
            Employe updatedEmploye = employeService.update(employe);
            return new ResponseEntity<>(updatedEmploye, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable("id") int id) {
        employeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String motDePasse) {
        boolean loginSuccessful = employeService.login(email, motDePasse);
        if (loginSuccessful) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User employe) {
        User registeredEmploye = employeService.register(employe);
        return new ResponseEntity<>(registeredEmploye, HttpStatus.CREATED);
    }
}

