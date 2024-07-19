package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FicheDePoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private String description;

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private RH responsable;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RH getResponsable() {
        return responsable;
    }

    public void setResponsable(RH responsable) {
        this.responsable = responsable;
    }
}
