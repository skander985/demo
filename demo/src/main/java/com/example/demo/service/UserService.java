//package com.example.demo.service;
//
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public abstract class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public boolean login(String email, String motDePasse) {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return passwordEncoder.matches(motDePasse, user.getMotDePasse());
//        }
//        return false; // or throw an exception if preferred
//    }
//
//    public User register(User user) {
//        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
//        return userRepository.save(user);
//    }
//}
package com.example.demo.service;

import com.example.demo.entity.User;

public abstract class UserService {

    public abstract boolean login(String email, String motDePasse);

    public abstract User register(User user);
}


