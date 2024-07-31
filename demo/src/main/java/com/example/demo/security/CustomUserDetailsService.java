package com.example.demo.security;



import com.example.demo.entity.User;
import com.example.demo.repository.AdministrateurRepository;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private EmployeRepository employeRepository;
//
//    @Autowired
//    private AdministrateurRepository administrateurRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = findUserByEmail(email);
//        System.out.println(user);
//        if (user != null) {
//            return new CustomUserDetails(user);
//        }
//        System.out.println("hello");
//        throw new UsernameNotFoundException("User not found with email: " + email);
//    }
//
//    private User findUserByEmail(String email) {
//        // Try UserRepository first
//        User user = userRepository.findByEmail(email).orElse(null);
//        System.out.println(user);
//        if (user != null) {
//            return user;
//        }
////
//        // Try EmployeRepository
//        user = employeRepository.findByEmail(email).orElse(null);
//        if (user != null) {
//            return user;
//        }
//
//        // Try AdministrateurRepository
//        user = administrateurRepository.findByEmail(email).orElse(null);
//        return user;
//
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
        User user = findUserByEmail(email);
        if (user != null) {
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }

    public UserDetails loadUserById(int id) throws UsernameNotFoundException {
        User user = findUserById(id);
        if (user != null) {
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found with ID: " + id);
    }

    private User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return user;
        }
        user = employeRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return user;
        }
        user = administrateurRepository.findByEmail(email).orElse(null);
        return user;
    }

    private User findUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }
        user = employeRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }
        user = administrateurRepository.findById(id).orElse(null);
        return user;
    }
}
