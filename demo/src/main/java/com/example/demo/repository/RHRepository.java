package com.example.demo.repository;

import com.example.demo.entity.RH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface RHRepository extends JpaRepository<RH, Integer> {
    Optional<RH> findByEmail(String email);
}
