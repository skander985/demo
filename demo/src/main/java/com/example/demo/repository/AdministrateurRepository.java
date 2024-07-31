package com.example.demo.repository;

import com.example.demo.entity.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {
    Optional<Administrateur> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Administrateur> findById(Integer id);
}
