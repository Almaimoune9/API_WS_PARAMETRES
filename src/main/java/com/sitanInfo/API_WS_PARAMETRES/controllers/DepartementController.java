package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.services.DepartementService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @Operation(summary = "Créer un departement")
    @PostMapping("/departement")
    public String create(@RequestBody Departement departement){
        return departementService.creer(departement);
    }

    @Operation(summary = "Afficher la liste des departement")
    @GetMapping("/departement")
    public List<Departement> read(){
        return departementService.lire();
    }

    @Operation(summary = "Afficher un departement")
    @GetMapping("/departement/{id}")
    public Optional<Departement> departement(@PathVariable int id){
        return departementService.findById(id);
    }

    @Operation(summary = "Modifier un departement")
    @PutMapping("/departement/{id}")
    public String update(@PathVariable int id, @RequestBody Departement departement){
        return departementService.modifier(id, departement);
    }

    @Operation(summary = "Supprimer un departement")
    @DeleteMapping("/departement/{id}")
    public String delete(@PathVariable int id){
        return departementService.supprimer(id);
    }
}
