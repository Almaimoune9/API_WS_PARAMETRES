package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.model.StatutEnseignant;
import com.sitanInfo.API_WS_PARAMETRES.services.StatutEnseignantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class StatutEnseignantController {

    @Autowired
    private StatutEnseignantService statutEnseignantService;

    @Operation(summary = "Ajouter un statut enseignant")
    @PostMapping("/statutEnseigant")
    public String creer(@RequestBody StatutEnseignant statutEnseignant){
        return statutEnseignantService.creer(statutEnseignant);
    }

    @Operation(summary = "Afficher la liste des statuts enseignants")
    @GetMapping("/statutEnseigant")
    public List<StatutEnseignant> read(){
        return statutEnseignantService.lire();
    }

    @Operation(summary = "Afficher un statut enseignant")
    @GetMapping("/statutEnseigant/{id}")
    public Optional<StatutEnseignant> statutEnseignant(@PathVariable int id){
        return statutEnseignantService.findById(id);
    }

    @Operation(summary = "Modifier un statut enseignant")
    @PutMapping("/statutEnseigant/{id}")
    public String update(@PathVariable int id, @RequestBody StatutEnseignant  statutEnseignant){
        return statutEnseignantService.modifier(id, statutEnseignant);
    }

    @Operation(summary = "Supprimer un statut enseignant")
    @DeleteMapping("/statutEnseigant/{id}")
    public String delete(@PathVariable int id){
        return statutEnseignantService.supprimer(id);
    }
}
