//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Employe extends User {
//    @ManyToOne
//    @JoinColumn(name = "administrateur_id")
//    private Administrateur administrateur;
//
//    @ManyToOne
//    @JoinColumn(name = "ficheDePoste_id")
//    private FicheDePoste ficheDePoste;
//
//    // Getters and setters
//    public Administrateur getAdministrateur() {
//        return administrateur;
//    }
//
//    public void setAdministrateur(Administrateur administrateur) {
//        this.administrateur = administrateur;
//    }
//
//    public FicheDePoste getFicheDePoste() {
//        return ficheDePoste;
//    }
//
//    public void setFicheDePoste(FicheDePoste ficheDePoste) {
//        this.ficheDePoste = ficheDePoste;
//    }
//}
//
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Employe extends User {
    @ManyToOne
    @JoinColumn(name = "administrateur_id")
    private Administrateur administrateur;

    @ManyToOne
    @JoinColumn(name = "ficheDePoste_id")
    private FicheDePoste ficheDePoste;

    // Constructor to set default role
    public Employe() {
        this.setRole("ROLE_EMPLOYE");
    }

    // Getters and setters
    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public FicheDePoste getFicheDePoste() {
        return ficheDePoste;
    }

    public void setFicheDePoste(FicheDePoste ficheDePoste) {
        this.ficheDePoste = ficheDePoste;
    }
}
