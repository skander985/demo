package com.example.demo.service;

import com.example.demo.entity.RH;

import com.example.demo.repository.RHRepository;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.repository.FicheDePosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.security.JwtTokenProvider;

import com.example.demo.entity.User;


import java.util.Optional;


import java.util.List;



@Service
public class RHService extends UserService{
    @Autowired
    private RHRepository rhRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FicheDePosteRepository ficheDePosteRepository;

    public List<RH> findAll() {
        return rhRepository.findAll();
    }

    public Optional<RH> findById(int id) {
        return rhRepository.findById(id);
    }

    public RH save(RH rh) {
        return rhRepository.save(rh);
    }

    public RH update(RH rh) {
        if (rhRepository.existsById(rh.getId())) {
            return rhRepository.save(rh);
        } else {
            throw new IllegalArgumentException("RH not found");
        }
    }

    public void deleteById(int id) {
        rhRepository.deleteById(id);
    }




    @Override
    public boolean login(String email, String motDePasse) {
        Optional<RH> rhOptional = rhRepository.findByEmail(email);
        if (rhOptional.isPresent()) {
            RH rh = rhOptional.get();
            if (passwordEncoder.matches(motDePasse, rh.getMotDePasse())) {
//                System.out.println("hello myfriend"+passwordEncoder.matches(motDePasse, rh.getMotDePasse())); // Print password matching check
                // Generate JWT token here
                return true;
            }
        }
        return false;
    }


    @Override
    public User register(User user) {
        if (user instanceof RH) {
            RH rh = (RH) user;
            rh.setMotDePasse(passwordEncoder.encode(rh.getMotDePasse()));
            return rhRepository.save(rh);
        }
        // Handle other user types or throw an exception if needed
        throw new IllegalArgumentException("Unsupported user type for registration");
    }
}

