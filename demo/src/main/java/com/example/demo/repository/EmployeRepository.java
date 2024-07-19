package com.example.demo.repository;

import com.example.demo.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    Optional<Employe> findByEmail(String email);
    Optional<Employe> findById(int id);
}

