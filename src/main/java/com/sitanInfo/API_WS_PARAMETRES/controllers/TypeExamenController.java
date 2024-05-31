package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeExamen;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import com.sitanInfo.API_WS_PARAMETRES.services.TypeExamenService;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class TypeExamenController {

    @Autowired
    private TypeExamenService typeExamenService;

    @Operation(summary = "Ajouter un type examen")
    @PostMapping("/typeExamen")
    public ResponseWrapper<TypeExamen> create(@RequestBody TypeExamen typeExamen){
        return typeExamenService.create(typeExamen);
    }

    @Operation(summary = "Afficher la liste des types examens")
    @GetMapping("/typeExamen")
    public List<TypeExamen> read(){
        return typeExamenService.findAll();
    }

    @Operation(summary = "Afficher un type examen")
    @GetMapping("/typeExamen/{id}")
    public Optional<TypeExamen> typeExamen(@PathVariable int id){
        return typeExamenService.findById(id);
    }

    @Operation(summary = "Modifier un type examen")
    @PutMapping("/typeExamen/{id}")
    public ResponseEntity<TypeExamen> update(@PathVariable int id, @RequestBody TypeExamen typeExamen){
        TypeExamen existingtypeExamen = typeExamenService.getTypeExamenRepository().getReferenceById(id);
        if (existingtypeExamen == null)
            return ResponseEntity.notFound().build();
        existingtypeExamen.setCode(typeExamen.getCode());
        existingtypeExamen.setLibelle(typeExamen.getLibelle());
        TypeExamen updateTypeExamen = typeExamenService.updateTypeExamen(existingtypeExamen);
        return ResponseEntity.ok(updateTypeExamen);
    }

    @Operation(summary = "Supprimer un type examen")
    @DeleteMapping("/typeExamen/{id}")
    public void delete(@PathVariable int id){
         typeExamenService.delete(id);
    }
}
