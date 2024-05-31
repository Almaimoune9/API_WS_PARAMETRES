package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.AnneeAcademique;
import com.sitanInfo.API_WS_PARAMETRES.services.AnneeAcademiqueService;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;

    @Operation(summary = "Créer une année academique")
    @PostMapping("/AnneeAcademique")
    public ResponseWrapper<AnneeAcademique> create(@RequestBody AnneeAcademique anneeAcademique){
        return anneeAcademiqueService.createAnneeAcademique(anneeAcademique);
    }

    @Operation(summary = "Afficher la liste anneeAcademique")
    @GetMapping("/AnneeAcademique")
    public List<AnneeAcademique> read(){
        return anneeAcademiqueService.findAll();
    }

    @Operation(summary = "Afficher une anneeAcademique")
    @GetMapping("/AnneeAcademique/{id}")
    public Optional<AnneeAcademique> anneeAcademique(@PathVariable int id){
        return anneeAcademiqueService.findById(id);
    }

    @Operation(summary = "Modifier une annee Academique")
    @PutMapping("/AnneeAcademique/{id}")
    public ResponseEntity<AnneeAcademique> updateAnneeAcademique(@PathVariable int id, @RequestBody AnneeAcademique anneeAcademique){
        AnneeAcademique existingAnnee = anneeAcademiqueService.getAnneeAcademiqueRepository().getById(id);
        if (existingAnnee == null)
            return ResponseEntity.notFound().build();
        existingAnnee.setAnneeAcademique(anneeAcademique.getAnneeAcademique());
        existingAnnee.setDateDeb(anneeAcademique.getDateDeb());
        existingAnnee.setDateFin(anneeAcademique.getDateFin());
        AnneeAcademique updateAnnee= anneeAcademiqueService.updateAnneeAcademique(existingAnnee);
        return ResponseEntity.ok(updateAnnee);
    }
    @Operation(summary = "Supprimer une Année Academique")
    @DeleteMapping("/AnneeAcademique/{id}")
    public ResponseEntity<?> deleteAnneeAcademique(@PathVariable int id){
        AnneeAcademique existeAnnee = anneeAcademiqueService.getAnneeAcademiqueRepository().getAnneeAcademiqueById(id);
        if (existeAnnee == null)
            return ResponseEntity.notFound().build();
        anneeAcademiqueService.delete(id);
        return ResponseEntity.ok().build();
    }
}
