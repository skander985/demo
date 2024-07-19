//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//public class Administrateur extends User {
//    @OneToMany(mappedBy = "administrateur", cascade = CascadeType.ALL)
//    private List<RH> rhList;
//
//    @OneToMany(mappedBy = "administrateur", cascade = CascadeType.ALL)
//    private List<Employe> employeList;
//
//    // Getters and setters
//    public List<RH> getRhList() {
//        return rhList;
//    }
//
//    public void setRhList(List<RH> rhList) {
//        this.rhList = rhList;
//    }
//
//    public List<Employe> getEmployeList() {
//        return employeList;
//    }
//
//    public void setEmployeList(List<Employe> employeList) {
//        this.employeList = employeList;
//    }
//}
//
package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Administrateur extends User {
    @OneToMany(mappedBy = "administrateur", cascade = CascadeType.ALL)
    private List<RH> rhList;

    @OneToMany(mappedBy = "administrateur", cascade = CascadeType.ALL)
    private List<Employe> employeList;

    // Getters and setters
    public List<RH> getRhList() {
        return rhList;
    }

    public void setRhList(List<RH> rhList) {
        this.rhList = rhList;
    }

    public List<Employe> getEmployeList() {
        return employeList;
    }

    public void setEmployeList(List<Employe> employeList) {
        this.employeList = employeList;
    }
}
