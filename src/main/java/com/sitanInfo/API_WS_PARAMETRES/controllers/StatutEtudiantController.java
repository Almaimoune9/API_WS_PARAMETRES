package com.sitanInfo.API_WS_PARAMETRES.controllers;


import com.sitanInfo.API_WS_PARAMETRES.model.Mention;
import com.sitanInfo.API_WS_PARAMETRES.model.StatutEtudiant;
import com.sitanInfo.API_WS_PARAMETRES.services.StatutEtudiantService;
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
public class StatutEtudiantController {

    @Autowired
    private StatutEtudiantService statutEtudiantService;

    @Operation(summary = "Ajouter un statut etudiant")
    @PostMapping("/statutEtudiant")
    public ResponseWrapper<StatutEtudiant> create(@RequestBody StatutEtudiant statutEtudiant){
        return statutEtudiantService.create(statutEtudiant);
    }

    @Operation(summary = "Afficher la liste des statuts etudiants")
    @GetMapping("/statutEtudiant")
    public List<StatutEtudiant> read(){
        return statutEtudiantService.findAllSatutEtudiant();
    }

    @Operation(summary = "Afficher un statut etudiant")
    @GetMapping("/statutEtudiant/{id}")
    public Optional<StatutEtudiant> statutEtudiant(@PathVariable int id){
        return statutEtudiantService.findById(id);
    }

    @Operation(summary = "Modifier un statut etudiant")
    @PutMapping("/statutEtudiant/{id}")
    public ResponseEntity<StatutEtudiant> updatestatutEtudiant(@PathVariable int id, @RequestBody StatutEtudiant statutEtudiant){
        StatutEtudiant existingstatutEtudiant = statutEtudiantService.getStatutEtudiantRepository().getById(id);
        if (existingstatutEtudiant == null)
            return ResponseEntity.notFound().build();
        existingstatutEtudiant.setCode(statutEtudiant.getCode());
        existingstatutEtudiant.setLibelle(statutEtudiant.getLibelle());
        StatutEtudiant updateStatutEtudiant= statutEtudiantService.updateStatutEtudiant(existingstatutEtudiant);
        return ResponseEntity.ok(updateStatutEtudiant);
    }

    @Operation(summary = "Supprimer un statutEtudiant")
    @DeleteMapping("/statutEtudiant/{id}")
    public ResponseEntity<StatutEtudiant> deleteStatutEtudiant(@PathVariable int id){
        StatutEtudiant existeStatutEtudiant = statutEtudiantService.getStatutEtudiantRepository().getById(id);
        if (existeStatutEtudiant == null)
            return ResponseEntity.notFound().build();
        statutEtudiantService.delete(id);
        return ResponseEntity.ok().build();
    }


    ////Pour la mention
    @Operation(summary = "Ajouter une mention")
    @PostMapping("/mention")
    public ResponseWrapper<Mention> create(@RequestBody Mention mention){
        return statutEtudiantService.createMention(mention);
    }

    @Operation(summary = "Afficher la liste des mentions")
    @GetMapping("/mention")
    public List<Mention> readMention(){
        return statutEtudiantService.findAllMention();
    }

    @Operation(summary = "Afficher une mention")
    @GetMapping("/mention/{id}")
    public Optional<Mention> statutMention(@PathVariable int id){
        return statutEtudiantService.findByIdMention(id);
    }

    @Operation(summary = "Modifier une mention")
    @PutMapping("/mention/{id}")
    public ResponseEntity<Mention> updateMention(@PathVariable int id, @RequestBody Mention mention){
        Mention existingMention = statutEtudiantService.getMentionRepository().getById(id);
        if (existingMention == null)
            return ResponseEntity.notFound().build();
        existingMention.setCode(mention.getCode());
        existingMention.setLibelle(mention.getLibelle());
        Mention updateMention= statutEtudiantService.updateMention(existingMention);
        return ResponseEntity.ok(updateMention);
    }

    @Operation(summary = "Supprimer une mention")
    @DeleteMapping("/mention/{id}")
    public ResponseEntity<Mention> deleteMention(@PathVariable int id){
        Mention existeMention = statutEtudiantService.getMentionRepository().getById(id);
        if (existeMention == null)
            return ResponseEntity.notFound().build();
        statutEtudiantService.deleteMention(id);
        return ResponseEntity.ok().build();
    }
}
