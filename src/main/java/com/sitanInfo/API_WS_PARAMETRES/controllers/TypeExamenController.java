package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeExamen;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import com.sitanInfo.API_WS_PARAMETRES.services.TypeExamenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class TypeExamenController {

    @Autowired
    private TypeExamenService typeExamenService;

    @Operation(summary = "Ajouter un type examen")
    @PostMapping("/typeexamen")
    public String creer(@RequestBody TypeExamen typeExamen){
        return typeExamenService.creer(typeExamen);
    }

    @Operation(summary = "Afficher la liste des types examens")
    @GetMapping("/typeexamen")
    public List<TypeExamen> read(){
        return typeExamenService.lire();
    }

    @Operation(summary = "Afficher un type examen")
    @GetMapping("/typeexamen/{id}")
    public Optional<TypeExamen> typeExamen(@PathVariable int id){
        return typeExamenService.findById(id);
    }

    @Operation(summary = "Modifier un type examen")
    @PutMapping("/typeexamen/{id}")
    public String update(@PathVariable int id, @RequestBody TypeExamen typeExamen){
        return typeExamenService.modifier(id, typeExamen);
    }

    @Operation(summary = "Supprimer un type examen")
    @DeleteMapping("/typeexamen/{id}")
    public String delete(@PathVariable int id){
        return typeExamenService.supprimer(id);
    }
}
