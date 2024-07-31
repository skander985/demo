//package com.example.demo.controller;
//
//import com.example.demo.entity.FicheDePoste;
//import com.example.demo.entity.Employe;
//import com.example.demo.service.FicheDePosteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/ficheDePoste")
//public class FicheDePosteController {
//
//    @Autowired
//    private FicheDePosteService ficheDePosteService;
//
//    @GetMapping
//    public List<FicheDePoste> getAllFichesDePoste() {
//        return ficheDePosteService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<FicheDePoste> getFicheDePosteById(@PathVariable int id) {
//        Optional<FicheDePoste> ficheDePoste = ficheDePosteService.findById(id);
//        return ficheDePoste.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public FicheDePoste createFicheDePoste(@RequestBody FicheDePoste ficheDePoste) {
//        return ficheDePosteService.save(ficheDePoste);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<FicheDePoste> updateFicheDePoste(@PathVariable int id, @RequestBody FicheDePoste ficheDePoste) {
//        if (ficheDePosteService.findById(id).isPresent()) {
//            ficheDePoste.setId(id);
//            return ResponseEntity.ok(ficheDePosteService.update(ficheDePoste));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteFicheDePoste(@PathVariable int id) {
//        if (ficheDePosteService.findById(id).isPresent()) {
//            ficheDePosteService.deleteById(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/associer")
////    public ResponseEntity<Void> associerFicheDePoste(@RequestParam int employeId, @RequestParam int ficheDePosteId) {
////        Optional<Employe> employe = ficheDePosteService.employeRepository.findById(employeId);
////        Optional<FicheDePoste> ficheDePoste = ficheDePosteService.findById(ficheDePosteId);
////        if (employe.isPresent() && ficheDePoste.isPresent()) {
////            ficheDePosteService.associerFicheDePoste(employe.get(), ficheDePoste.get());
////            return ResponseEntity.ok().build();
////        } else {
////            return ResponseEntity.notFound().build();
////        }
////    }
//
//    public ResponseEntity<Void> associerFicheDePoste(@RequestParam int employeId, @RequestParam int ficheDePosteId) {
//        Optional<Employe> employe = ficheDePosteService.findEmployeById(employeId);
//        Optional<FicheDePoste> ficheDePoste = ficheDePosteService.findById(ficheDePosteId);
//        if (employe.isPresent() && ficheDePoste.isPresent()) {
//            ficheDePosteService.associerFicheDePoste(employe.get(), ficheDePoste.get());
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/voir/{employeId}")
//    public ResponseEntity<FicheDePoste> voirFicheDePoste(@PathVariable int employeId) {
//        FicheDePoste ficheDePoste = ficheDePosteService.voirFicheDePoste(employeId);
//        if (ficheDePoste != null) {
//            return ResponseEntity.ok(ficheDePoste);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/search")
//    public List<FicheDePoste> searchFichesDePoste(
//            @RequestParam(required = false) String titre,
//            @RequestParam(required = false) String description,
//            @RequestParam(required = false) String nomResponsable) {
//        return ficheDePosteService.search(titre, description, nomResponsable);
//    }
//
//    @GetMapping("/export")
//    public ResponseEntity<Void> exportFicheDePoste(
//            @RequestParam int ficheDePosteId,
//            @RequestParam String format) {
//        Optional<FicheDePoste> ficheDePoste = ficheDePosteService.findById(ficheDePosteId);
//        if (ficheDePoste.isPresent()) {
//            ficheDePosteService.exportDonnees(ficheDePoste.get(), format);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
package com.example.demo.controller;

import com.example.demo.entity.FicheDePoste;
import com.example.demo.entity.Employe;
import com.example.demo.service.FicheDePosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ficheDePoste")
public class FicheDePosteController {

    @Autowired
    private FicheDePosteService ficheDePosteService;

    @GetMapping
    public List<FicheDePoste> getAllFichesDePoste() {
        return ficheDePosteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FicheDePoste> getFicheDePosteById(@PathVariable int id) {
        Optional<FicheDePoste> ficheDePoste = ficheDePosteService.findById(id);
        return ficheDePoste.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FicheDePoste createFicheDePoste(@RequestBody FicheDePoste ficheDePoste) {
        return ficheDePosteService.save(ficheDePoste);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FicheDePoste> updateFicheDePoste(@PathVariable int id, @RequestBody FicheDePoste ficheDePoste) {
        if (ficheDePosteService.findById(id).isPresent()) {
            ficheDePoste.setId(id);
            return ResponseEntity.ok(ficheDePosteService.update(ficheDePoste));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheDePoste(@PathVariable int id) {
        if (ficheDePosteService.findById(id).isPresent()) {
            ficheDePosteService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/associer")
    public ResponseEntity<Void> associerFicheDePoste(@RequestParam int employeId, @RequestParam int ficheDePosteId) {
        Optional<Employe> employe = ficheDePosteService.findEmployeById(employeId);
        Optional<FicheDePoste> ficheDePoste = ficheDePosteService.findById(ficheDePosteId);
        if (employe.isPresent() && ficheDePoste.isPresent()) {
            ficheDePosteService.associerFicheDePoste(employe.get(), ficheDePoste.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/voir/{employeId}")
    public ResponseEntity<FicheDePoste> voirFicheDePoste(@PathVariable int employeId) {
        FicheDePoste ficheDePoste = ficheDePosteService.voirFicheDePoste(employeId);
        if (ficheDePoste != null) {
            return ResponseEntity.ok(ficheDePoste);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<FicheDePoste> searchFichesDePoste(
            @RequestParam(required = false) String mission,
            @RequestParam(required = false) String competence,
            @RequestParam(required = false) String nomSuperieur) {
        return ficheDePosteService.search(mission, competence, nomSuperieur);
    }

    @GetMapping("/export")
    public ResponseEntity<Void> exportFicheDePoste(
            @RequestParam int ficheDePosteId,
            @RequestParam String format) {
        Optional<FicheDePoste> ficheDePoste = ficheDePosteService.findById(ficheDePosteId);
        if (ficheDePoste.isPresent()) {
            ficheDePosteService.exportDonnees(ficheDePoste.get(), format);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
