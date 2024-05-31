package com.sitanInfo.API_WS_PARAMETRES.controllers;


import com.sitanInfo.API_WS_PARAMETRES.model.StatutEnseignant;
import com.sitanInfo.API_WS_PARAMETRES.services.StatutEnseignantService;
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
public class StatutEnseignantController {

    @Autowired
    private StatutEnseignantService statutEnseignantService;

    @Operation(summary = "Ajouter un statut enseignant")
    @PostMapping("/statutEnseigant")
    public ResponseWrapper<StatutEnseignant> create(@RequestBody StatutEnseignant statutEnseignant){
        return statutEnseignantService.create(statutEnseignant);
    }

    @Operation(summary = "Afficher la liste des statuts enseignants")
    @GetMapping("/statutEnseigant")
    public List<StatutEnseignant> read(){
        return statutEnseignantService.findAll();
    }

    @Operation(summary = "Afficher un statut enseignant")
    @GetMapping("/statutEnseigant/{id}")
    public Optional<StatutEnseignant> statutEnseignant(@PathVariable int id){
        return statutEnseignantService.findById(id);
    }

    @Operation(summary = "Modifier un statut enseignant")
    @PutMapping("/statutEnseigant/{id}")
    public ResponseEntity<StatutEnseignant> update(@PathVariable int id, @RequestBody StatutEnseignant  statutEnseignant){
        StatutEnseignant existingStatutEnseignant = statutEnseignantService.getStatutEnseignantRepository().getById(id);
        if (existingStatutEnseignant == null)
            return ResponseEntity.notFound().build();
        existingStatutEnseignant.setCode(statutEnseignant.getCode());
        existingStatutEnseignant.setLibelle(statutEnseignant.getLibelle());
        StatutEnseignant updateStatutEnseignant= statutEnseignantService.updateStatutEnseignant(existingStatutEnseignant);
        return ResponseEntity.ok(updateStatutEnseignant);
    }

    @Operation(summary = "Supprimer un statut enseignant")
    @DeleteMapping("/statutEnseigant/{id}")
    public void delete(@PathVariable int id){
         statutEnseignantService.delete(id);
    }
}
