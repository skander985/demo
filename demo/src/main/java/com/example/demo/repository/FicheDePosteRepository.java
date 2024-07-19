package com.example.demo.repository;

import com.example.demo.entity.FicheDePoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FicheDePosteRepository extends JpaRepository<FicheDePoste, Integer> {
    List<FicheDePoste> findByTitreContaining(String titre);
    List<FicheDePoste> findByDescriptionContaining(String description);
    List<FicheDePoste> findByResponsableNomContaining(String nomResponsable);
    List<FicheDePoste> findByTitreContainingAndDescriptionContainingAndResponsableNomContaining(String titre, String description, String nomResponsable);
    List<FicheDePoste> findByTitreContainingAndDescriptionContaining(String titre, String description);
    List<FicheDePoste> findByTitreContainingAndResponsableNomContaining(String titre, String nomResponsable);
    List<FicheDePoste> findByDescriptionContainingAndResponsableNomContaining(String description, String nomResponsable);
}

