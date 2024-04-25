package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Etablissement;
import com.sitanInfo.API_WS_PARAMETRES.services.EtablissementService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class EtablissementController {

    @Autowired
    private EtablissementService etablissementService;

    @Operation(summary = "Créer un etablissement")
    @PostMapping("/etablissement")
    public String creer(@RequestBody Etablissement etablissement){
        return etablissementService.creer(etablissement);
    }

    @Operation(summary = "Afficher la liste des etablissements")
    @GetMapping("/etablissement")
    public List<Etablissement>  read(){
        return etablissementService.lire();
    }

    @Operation(summary = "Modifier un etablissement")
    @PutMapping("/etablissement/{id}")
    public String update(@PathVariable int id, @RequestBody Etablissement etablissement){
        return etablissementService.modifier(id, etablissement);
    }

    @Operation(summary = "Afficher un etablissement")
    @GetMapping("/etablissement/{id}")
    public Optional<Etablissement> etablissement(@PathVariable int id){
        return etablissementService.findById(id);
    }

    @Operation(summary = "Supprimer un etablissement")
    @DeleteMapping("/etablissement/{id}")
    public String delete(@PathVariable int id){
        return etablissementService.supprimer(id);
    }
}
