package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.AnneeAcademique;
import com.sitanInfo.API_WS_PARAMETRES.services.AnneeAcademiqueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;

    @Operation(summary = "Créer une année academique")
    @PostMapping("AnneeAcademique")
    public AnneeAcademique create(@RequestBody AnneeAcademique anneeAcademique){
        return anneeAcademiqueService.creerAnneeAcademique(anneeAcademique);
    }

    @Operation(summary = "Afficher la liste anneeAcademique")
    @GetMapping("AnneeAcademique")
    public List<AnneeAcademique> read(){
        return anneeAcademiqueService.lire();
    }

    @Operation(summary = "Afficher une anneeAcademique")
    @GetMapping("AnneeAcademique/{id}")
    public Optional<AnneeAcademique> anneeAcademique(@PathVariable int id){
        return anneeAcademiqueService.findById(id);
    }

    @Operation(summary = "Supprimer une anneeAcademique")
    @DeleteMapping("AnneeAcademique/{id}")
    public String delete(@PathVariable int id){
        return anneeAcademiqueService.supprimer(id);
    }
}
