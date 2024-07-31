//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//public class FicheDePoste {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String titre;
//    private String description;
//
//    @ManyToOne
//    @JoinColumn(name = "rh_id")
//    private RH responsable;
//
//    // Getters and setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitre() {
//        return titre;
//    }
//
//    public void setTitre(String titre) {
//        this.titre = titre;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public RH getResponsable() {
//        return responsable;
//    }
//
//    public void setResponsable(RH responsable) {
//        this.responsable = responsable;
//    }
//}
package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class FicheDePoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mission;
    private String natureDuPoste;
    private String superieurHierarchique;
    private String supervision;

    @ElementCollection
    private List<String> activitesEtTaches;

    @ElementCollection
    private List<String> competencesTechniques;

    @ElementCollection
    private List<String> aptitudesProfessionnelles;

    private String conditionsParticulieresExercice;

    @ElementCollection
    private List<String> postesPrecedents;

    @ElementCollection
    private List<String> evolutionProfessionnelle;

    private String diplomeRequis;

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

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getNatureDuPoste() {
        return natureDuPoste;
    }

    public void setNatureDuPoste(String natureDuPoste) {
        this.natureDuPoste = natureDuPoste;
    }

    public String getSuperieurHierarchique() {
        return superieurHierarchique;
    }

    public void setSuperieurHierarchique(String superieurHierarchique) {
        this.superieurHierarchique = superieurHierarchique;
    }

    public String getSupervision() {
        return supervision;
    }

    public void setSupervision(String supervision) {
        this.supervision = supervision;
    }

    public List<String> getActivitesEtTaches() {
        return activitesEtTaches;
    }

    public void setActivitesEtTaches(List<String> activitesEtTaches) {
        this.activitesEtTaches = activitesEtTaches;
    }

    public List<String> getCompetencesTechniques() {
        return competencesTechniques;
    }

    public void setCompetencesTechniques(List<String> competencesTechniques) {
        this.competencesTechniques = competencesTechniques;
    }

    public List<String> getAptitudesProfessionnelles() {
        return aptitudesProfessionnelles;
    }

    public void setAptitudesProfessionnelles(List<String> aptitudesProfessionnelles) {
        this.aptitudesProfessionnelles = aptitudesProfessionnelles;
    }

    public String getConditionsParticulieresExercice() {
        return conditionsParticulieresExercice;
    }

    public void setConditionsParticulieresExercice(String conditionsParticulieresExercice) {
        this.conditionsParticulieresExercice = conditionsParticulieresExercice;
    }

    public List<String> getPostesPrecedents() {
        return postesPrecedents;
    }

    public void setPostesPrecedents(List<String> postesPrecedents) {
        this.postesPrecedents = postesPrecedents;
    }

    public List<String> getEvolutionProfessionnelle() {
        return evolutionProfessionnelle;
    }

    public void setEvolutionProfessionnelle(List<String> evolutionProfessionnelle) {
        this.evolutionProfessionnelle = evolutionProfessionnelle;
    }

    public String getDiplomeRequis() {
        return diplomeRequis;
    }

    public void setDiplomeRequis(String diplomeRequis) {
        this.diplomeRequis = diplomeRequis;
    }

    public RH getResponsable() {
        return responsable;
    }

    public void setResponsable(RH responsable) {
        this.responsable = responsable;
    }
}
