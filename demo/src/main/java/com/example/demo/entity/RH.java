//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//public class RH extends User {
//    @ManyToOne
//    @JoinColumn(name = "administrateur_id")
//    private Administrateur administrateur;
//
//    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL)
//    private List<FicheDePoste> ficheDePosteList;
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
//    public List<FicheDePoste> getFicheDePosteList() {
//        return ficheDePosteList;
//    }
//
//    public void setFicheDePosteList(List<FicheDePoste> ficheDePosteList) {
//        this.ficheDePosteList = ficheDePosteList;
//    }
//}
//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//public class RH extends User {
//    @ManyToOne
//    @JoinColumn(name = "administrateur_id")
//    private Administrateur administrateur;
//
//    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL)
//    private List<FicheDePoste> ficheDePosteList;
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
//    public List<FicheDePoste> getFicheDePosteList() {
//        return ficheDePosteList;
//    }
//
//    public void setFicheDePosteList(List<FicheDePoste> ficheDePosteList) {
//        this.ficheDePosteList = ficheDePosteList;
//    }
//}
package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class RH extends User {
    @ManyToOne
    @JoinColumn(name = "administrateur_id")
    private Administrateur administrateur;

    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL)
    private List<FicheDePoste> ficheDePosteList;

    // Constructor to set default role
    public RH() {
        this.setRole("ROLE_RH");
    }

    // Getters and setters
    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public List<FicheDePoste> getFicheDePosteList() {
        return ficheDePosteList;
    }

    public void setFicheDePosteList(List<FicheDePoste> ficheDePosteList) {
        this.ficheDePosteList = ficheDePosteList;
    }
}
