package com.example.demo.service;

import com.example.demo.entity.Employe;

import com.example.demo.repository.EmployeRepository;
import com.example.demo.repository.FicheDePosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;


import java.util.Optional;

import java.util.List;


@Service
public class EmployeService extends UserService{
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private FicheDePosteRepository ficheDePosteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    public Optional<Employe> findById(int id) {
        return employeRepository.findById(id);
    }

    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }

    public Employe update(Employe employe) {
        if (employeRepository.existsById(employe.getId())) {
            return employeRepository.save(employe);
        } else {
            throw new IllegalArgumentException("Employe not found");
        }
    }

    public void deleteById(int id) {
        employeRepository.deleteById(id);
    }




    @Override
    public boolean login(String email, String motDePasse) {
        Optional<Employe> employeOptional = employeRepository.findByEmail(email);
        if (employeOptional.isPresent()) {
            Employe employe = employeOptional.get();
            return passwordEncoder.matches(motDePasse, employe.getMotDePasse());
        }
        return false; // or throw an exception if preferred
    }

    @Override
    public User register(User user) {
        if (user instanceof Employe) {
            Employe employe = (Employe) user;
            employe.setMotDePasse(passwordEncoder.encode(employe.getMotDePasse()));
            return employeRepository.save(employe);
        }
        // Handle other user types or throw an exception if needed
        throw new IllegalArgumentException("Unsupported user type for registration");
    }
    public boolean existsByEmail(String email) {
        return employeRepository.existsByEmail(email);
    }
}

