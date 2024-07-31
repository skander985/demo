//package com.example.demo.service;
//
//import com.example.demo.entity.Employe;
//import com.example.demo.entity.FicheDePoste;
//import com.example.demo.repository.EmployeRepository;
//import com.example.demo.repository.FicheDePosteRepository;
//import com.example.demo.repository.RHRepository;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.opencsv.CSVWriter;
//
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class FicheDePosteService {
//    @Autowired
//    private FicheDePosteRepository ficheDePosteRepository;
//    @Autowired
//    private RHRepository rhRepository;
//
//    @Autowired
//    private EmployeRepository employeRepository;
//
//    public Optional<Employe> findEmployeById(int id) {
//        return employeRepository.findById(id);
//    }
//
//    public List<FicheDePoste> findAll() {
//        return ficheDePosteRepository.findAll();
//    }
//
//    public Optional<FicheDePoste> findById(int id) {
//        return ficheDePosteRepository.findById(id);
//    }
//
//    public FicheDePoste save(FicheDePoste ficheDePoste) {
//        return ficheDePosteRepository.save(ficheDePoste);
//    }
//
//    public FicheDePoste update(FicheDePoste ficheDePoste) {
//        if (ficheDePosteRepository.existsById(ficheDePoste.getId())) {
//            return ficheDePosteRepository.save(ficheDePoste);
//        } else {
//            throw new IllegalArgumentException("FicheDePoste not found");
//        }
//    }
//
//    public void deleteById(int id) {
//        ficheDePosteRepository.deleteById(id);
//    }
//    public void associerFicheDePoste(Employe collaborateur, FicheDePoste ficheDePoste) {
//        if (employeRepository.existsById(collaborateur.getId()) && ficheDePosteRepository.existsById(ficheDePoste.getId())) {
//            collaborateur.setFicheDePoste(ficheDePoste);
//            employeRepository.save(collaborateur);
//        } else {
//            throw new IllegalArgumentException("Collaborateur or FicheDePoste not found");
//        }
//    }
//    public FicheDePoste voirFicheDePoste(int employeId) {
//        Employe employe = employeRepository.findById(employeId).orElse(null);
//        if (employe != null) {
//            return employe.getFicheDePoste();
//        }
//        return null;
//    }
//
//    public void exportDonnees(FicheDePoste ficheDePoste, String format) {
//        if ("CSV".equalsIgnoreCase(format)) {
//            exportToCSV(ficheDePoste);
//        } else if ("Excel".equalsIgnoreCase(format)) {
//            exportToExcel(ficheDePoste);
//        } else {
//            throw new IllegalArgumentException("Unsupported format: " + format);
//        }
//    }
//
//    private void exportToCSV(FicheDePoste ficheDePoste) {
//        String csvFile = "fiche_de_poste.csv";
//        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
//            String[] header = {"ID", "Titre", "Description", "Responsable"};
//            writer.writeNext(header);
//
//            String[] data = {
//                    String.valueOf(ficheDePoste.getId()),
//                    ficheDePoste.getTitre(),
//                    ficheDePoste.getDescription(),
//                    ficheDePoste.getResponsable().getNom() + " " + ficheDePoste.getResponsable().getPrenom()
//            };
//            writer.writeNext(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void exportToExcel(FicheDePoste ficheDePoste) {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Fiche De Poste");
//
//        Row headerRow = sheet.createRow(0);
//        String[] columns = {"ID", "Titre", "Description", "Responsable"};
//        for (int i = 0; i < columns.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columns[i]);
//        }
//
//        Row dataRow = sheet.createRow(1);
//        dataRow.createCell(0).setCellValue(ficheDePoste.getId());
//        dataRow.createCell(1).setCellValue(ficheDePoste.getTitre());
//        dataRow.createCell(2).setCellValue(ficheDePoste.getDescription());
//        dataRow.createCell(3).setCellValue(ficheDePoste.getResponsable().getNom() + " " + ficheDePoste.getResponsable().getPrenom());
//
//        try (FileOutputStream fileOut = new FileOutputStream("fiche_de_poste.xlsx")) {
//            workbook.write(fileOut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public List<FicheDePoste> searchByTitre(String titre) {
//        return ficheDePosteRepository.findByTitreContaining(titre);
//    }
//
//    public List<FicheDePoste> searchByDescription(String description) {
//        return ficheDePosteRepository.findByDescriptionContaining(description);
//    }
//
//    public List<FicheDePoste> searchByResponsable(String nomResponsable) {
//        return ficheDePosteRepository.findByResponsableNomContaining(nomResponsable);
//    }
//
//    public List<FicheDePoste> search(String titre, String description, String nomResponsable) {
//        if (titre != null && description != null && nomResponsable != null) {
//            return ficheDePosteRepository.findByTitreContainingAndDescriptionContainingAndResponsableNomContaining(titre, description, nomResponsable);
//        } else if (titre != null && description != null) {
//            return ficheDePosteRepository.findByTitreContainingAndDescriptionContaining(titre, description);
//        } else if (titre != null && nomResponsable != null) {
//            return ficheDePosteRepository.findByTitreContainingAndResponsableNomContaining(titre, nomResponsable);
//        } else if (description != null && nomResponsable != null) {
//            return ficheDePosteRepository.findByDescriptionContainingAndResponsableNomContaining(description, nomResponsable);
//        } else if (titre != null) {
//            return searchByTitre(titre);
//        } else if (description != null) {
//            return searchByDescription(description);
//        } else if (nomResponsable != null) {
//            return searchByResponsable(nomResponsable);
//        } else {
//            return findAll();
//        }
//    }
//}
package com.example.demo.service;

import com.example.demo.entity.Employe;
import com.example.demo.entity.FicheDePoste;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.repository.FicheDePosteRepository;
import com.example.demo.repository.RHRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVWriter;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FicheDePosteService {
    @Autowired
    private FicheDePosteRepository ficheDePosteRepository;

    @Autowired
    private RHRepository rhRepository;

    @Autowired
    private EmployeRepository employeRepository;

    public Optional<Employe> findEmployeById(int id) {
        return employeRepository.findById(id);
    }

    public List<FicheDePoste> findAll() {
        return ficheDePosteRepository.findAll();
    }

    public Optional<FicheDePoste> findById(int id) {
        return ficheDePosteRepository.findById(id);
    }

    public FicheDePoste save(FicheDePoste ficheDePoste) {
        return ficheDePosteRepository.save(ficheDePoste);
    }

    public FicheDePoste update(FicheDePoste ficheDePoste) {
        if (ficheDePosteRepository.existsById(ficheDePoste.getId())) {
            return ficheDePosteRepository.save(ficheDePoste);
        } else {
            throw new IllegalArgumentException("FicheDePoste not found");
        }
    }

    public void deleteById(int id) {
        ficheDePosteRepository.deleteById(id);
    }

    public void associerFicheDePoste(Employe collaborateur, FicheDePoste ficheDePoste) {
        if (employeRepository.existsById(collaborateur.getId()) && ficheDePosteRepository.existsById(ficheDePoste.getId())) {
            collaborateur.setFicheDePoste(ficheDePoste);
            employeRepository.save(collaborateur);
        } else {
            throw new IllegalArgumentException("Collaborateur or FicheDePoste not found");
        }
    }

    public FicheDePoste voirFicheDePoste(int employeId) {
        Employe employe = employeRepository.findById(employeId).orElse(null);
        if (employe != null) {
            return employe.getFicheDePoste();
        }
        return null;
    }

    public void exportDonnees(FicheDePoste ficheDePoste, String format) {
        if ("CSV".equalsIgnoreCase(format)) {
            exportToCSV(ficheDePoste);
        } else if ("Excel".equalsIgnoreCase(format)) {
            exportToExcel(ficheDePoste);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    private void exportToCSV(FicheDePoste ficheDePoste) {
        String csvFile = "fiche_de_poste.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            String[] header = {
                    "ID", "Mission", "Nature du Poste", "Supérieur Hiérarchique", "Supervision",
                    "Activités et Tâches", "Compétences Techniques", "Aptitudes Professionnelles",
                    "Conditions Particulières d'Exercice", "Postes Précédents", "Evolution Professionnelle", "Diplôme Requis", "Responsable"
            };
            writer.writeNext(header);

            String[] data = {
                    String.valueOf(ficheDePoste.getId()),
                    ficheDePoste.getMission(),
                    ficheDePoste.getNatureDuPoste(),
                    ficheDePoste.getSuperieurHierarchique(),
                    ficheDePoste.getSupervision(),
                    String.join(", ", ficheDePoste.getActivitesEtTaches()),
                    String.join(", ", ficheDePoste.getCompetencesTechniques()),
                    String.join(", ", ficheDePoste.getAptitudesProfessionnelles()),
                    ficheDePoste.getConditionsParticulieresExercice(),
                    String.join(", ", ficheDePoste.getPostesPrecedents()),
                    String.join(", ", ficheDePoste.getEvolutionProfessionnelle()),
                    ficheDePoste.getDiplomeRequis(),
                    ficheDePoste.getResponsable().getNom() + " " + ficheDePoste.getResponsable().getPrenom()
            };
            writer.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportToExcel(FicheDePoste ficheDePoste) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Fiche De Poste");

        Row headerRow = sheet.createRow(0);
        String[] columns = {
                "ID", "Mission", "Nature du Poste", "Supérieur Hiérarchique", "Supervision",
                "Activités et Tâches", "Compétences Techniques", "Aptitudes Professionnelles",
                "Conditions Particulières d'Exercice", "Postes Précédents", "Evolution Professionnelle", "Diplôme Requis", "Responsable"
        };
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(ficheDePoste.getId());
        dataRow.createCell(1).setCellValue(ficheDePoste.getMission());
        dataRow.createCell(2).setCellValue(ficheDePoste.getNatureDuPoste());
        dataRow.createCell(3).setCellValue(ficheDePoste.getSuperieurHierarchique());
        dataRow.createCell(4).setCellValue(ficheDePoste.getSupervision());
        dataRow.createCell(5).setCellValue(String.join(", ", ficheDePoste.getActivitesEtTaches()));
        dataRow.createCell(6).setCellValue(String.join(", ", ficheDePoste.getCompetencesTechniques()));
        dataRow.createCell(7).setCellValue(String.join(", ", ficheDePoste.getAptitudesProfessionnelles()));
        dataRow.createCell(8).setCellValue(ficheDePoste.getConditionsParticulieresExercice());
        dataRow.createCell(9).setCellValue(String.join(", ", ficheDePoste.getPostesPrecedents()));
        dataRow.createCell(10).setCellValue(String.join(", ", ficheDePoste.getEvolutionProfessionnelle()));
        dataRow.createCell(11).setCellValue(ficheDePoste.getDiplomeRequis());
        dataRow.createCell(12).setCellValue(ficheDePoste.getResponsable().getNom() + " " + ficheDePoste.getResponsable().getPrenom());

        try (FileOutputStream fileOut = new FileOutputStream("fiche_de_poste.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<FicheDePoste> searchByMission(String mission) {
        return ficheDePosteRepository.findByMissionContaining(mission);
    }

    public List<FicheDePoste> searchByCompetence(String competence) {
        return ficheDePosteRepository.findByCompetencesTechniquesContaining(competence);
    }

    public List<FicheDePoste> searchBySuperieur(String nomSuperieur) {
        return ficheDePosteRepository.findBySuperieurHierarchiqueContaining(nomSuperieur);
    }

    public List<FicheDePoste> search(String mission, String competence, String nomSuperieur) {
        if (mission != null && competence != null && nomSuperieur != null) {
            return ficheDePosteRepository.findByMissionContainingAndCompetencesTechniquesContainingAndSuperieurHierarchiqueContaining(mission, competence, nomSuperieur);
        } else if (mission != null && competence != null) {
            return ficheDePosteRepository.findByMissionContainingAndCompetencesTechniquesContaining(mission, competence);
        } else if (mission != null && nomSuperieur != null) {
            return ficheDePosteRepository.findByMissionContainingAndSuperieurHierarchiqueContaining(mission, nomSuperieur);
        } else if (competence != null && nomSuperieur != null) {
            return ficheDePosteRepository.findByCompetencesTechniquesContainingAndSuperieurHierarchiqueContaining(competence, nomSuperieur);
        } else if (mission != null) {
            return searchByMission(mission);
        } else if (competence != null) {
            return searchByCompetence(competence);
        } else if (nomSuperieur != null) {
            return searchBySuperieur(nomSuperieur);
        } else {
            return findAll();
        }
    }
}

