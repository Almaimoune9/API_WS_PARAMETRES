package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Comptes;
import com.sitanInfo.API_WS_PARAMETRES.services.ComptesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CompteController {

    @Autowired
    private ComptesService comptesService;

    @Operation(summary = "Créer un compte")
    @PostMapping("/compte")
    public String create(@RequestBody Comptes comptes){
        return comptesService.creer(comptes);
    }

    @Operation(summary = "Afficher la liste des comptes")
    @GetMapping("/compte")
    public List<Comptes> read(){
        return comptesService.lire();
    }

    @Operation(summary = "Afficher un compte")
    @GetMapping("/compte/{id}")
    public Optional<Comptes> comptes(@PathVariable int id){
        return comptesService.findById(id);
    }

    @Operation(summary = "Modifier un compte")
    @PutMapping("/compte/{id}")
    public String update(@PathVariable int id, @RequestBody Comptes comptes){
        return comptesService.modifier(id, comptes);
    }

    @Operation(summary = "Supprimer un compte")
    @DeleteMapping("/compte/{id}")
    public String delete(@PathVariable int id){
        return comptesService.supprimer(id);
    }
}
