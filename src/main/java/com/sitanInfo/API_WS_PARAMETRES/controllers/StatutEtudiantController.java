package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.StatutEnseignant;
import com.sitanInfo.API_WS_PARAMETRES.model.StatutEtudiant;
import com.sitanInfo.API_WS_PARAMETRES.services.StatutEtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class StatutEtudiantController {

    @Autowired
    private StatutEtudiantService statutEtudiantService;

    @Operation(summary = "Ajouter un statut etudiant")
    @PostMapping("/statutEtudiant")
    public String creer(@RequestBody StatutEtudiant statutEtudiant){
        return statutEtudiantService.creer(statutEtudiant);
    }

    @Operation(summary = "Afficher la liste des statuts etudiants")
    @GetMapping("/statutEtudiant")
    public List<StatutEtudiant> read(){
        return statutEtudiantService.lire();
    }

    @Operation(summary = "Afficher un statut etudiant")
    @GetMapping("/statutEtudiant/{id}")
    public Optional<StatutEtudiant> statutEtudiant(@PathVariable int id){
        return statutEtudiantService.findById(id);
    }

    @Operation(summary = "Modifier un statut etudiant")
    @PutMapping("/statutEtudiant/{id}")
    public String update(@PathVariable int id, @RequestBody StatutEtudiant  statutEtudiant){
        return statutEtudiantService.modifier(id, statutEtudiant);
    }

    @Operation(summary = "Supprimer un statut etudiant")
    @DeleteMapping("/statutEtudiant/{id}")
    public String delete(@PathVariable int id){
        return statutEtudiantService.supprimer(id);
    }
}
