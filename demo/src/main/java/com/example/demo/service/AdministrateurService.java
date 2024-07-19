package com.example.demo.service;

import com.example.demo.entity.Administrateur;

import com.example.demo.repository.AdministrateurRepository;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.repository.FicheDePosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;

import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class AdministrateurService extends UserService{
    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private FicheDePosteRepository ficheDePosteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Administrateur> findAll() {
        return administrateurRepository.findAll();
    }

    public Optional<Administrateur> findById(int id) {
        return administrateurRepository.findById(id);
    }

    public Administrateur save(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public Administrateur update(Administrateur administrateur) {
        if (administrateurRepository.existsById(administrateur.getId())) {
            return administrateurRepository.save(administrateur);
        } else {
            throw new IllegalArgumentException("Administrateur not found");
        }
    }

    public void deleteById(int id) {
        administrateurRepository.deleteById(id);
    }




    @Override
    public boolean login(String email, String motDePasse) {
        Optional<Administrateur> adminOptional = administrateurRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            Administrateur admin = adminOptional.get();
            return passwordEncoder.matches(motDePasse, admin.getMotDePasse());
        }
        return false; // or throw an exception if preferred
    }

    @Override
    public User register(User user) {
        if (user instanceof Administrateur) {
            Administrateur admin = (Administrateur) user;
            admin.setMotDePasse(passwordEncoder.encode(admin.getMotDePasse()));
            return administrateurRepository.save(admin);
        }
        // Handle other user types or throw an exception if needed
        throw new IllegalArgumentException("Unsupported user type for registration");
    }
}


