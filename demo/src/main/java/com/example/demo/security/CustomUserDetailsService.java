package com.example.demo.security;
//
//import com.example.demo.entity.RH;
//import com.example.demo.repository.RHRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private RHRepository rhRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        RH rh = rhRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(email)
//                .password(rh.getMotDePasse())
//                .roles("RH") // Assuming RH has a role
//                .build();
//    }
//}
//


import com.example.demo.entity.User;
import com.example.demo.repository.AdministrateurRepository;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//        return new CustomUserDetails(user);
//    }
//}
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Try UserRepository first
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return new CustomUserDetails(user);
        }

        // Try EmployeRepository
        User employe = employeRepository.findByEmail(email).orElse(null);
        if (employe != null) {
            return new CustomUserDetails(employe);
        }

        // Try AdministrateurRepository
        User administrateur = administrateurRepository.findByEmail(email).orElse(null);
        if (administrateur != null) {
            return new CustomUserDetails(administrateur);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
