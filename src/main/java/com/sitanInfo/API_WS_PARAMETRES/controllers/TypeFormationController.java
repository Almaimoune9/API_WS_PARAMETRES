package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeFormation;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import com.sitanInfo.API_WS_PARAMETRES.services.TypeFormationService;
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
public class TypeFormationController {

    @Autowired
    private TypeFormationService typeFormationService;

    @Operation(summary = "Créer un type formation")
    @PostMapping("/typeFormation")
    public ResponseWrapper<TypeFormation> create(@RequestBody TypeFormation typeFormation){
        return typeFormationService.create(typeFormation);
    }

    @Operation(summary = "Afficher la liste des types formations")
    @GetMapping("/typeFormation")
    public List<TypeFormation> read(){
        return typeFormationService.findAll();
    }

    @Operation(summary = "Afficher un type formation")
    @GetMapping("/typeFormation/{id}")
    public Optional<TypeFormation> typeFormation(@PathVariable int id){
        return typeFormationService.findById(id);
    }

    @Operation(summary = "Modifier un type formation")
    @PutMapping("/typeFormation/{id}")
    public ResponseEntity<TypeFormation> update(@PathVariable int id, @RequestBody TypeFormation typeFormation){
        TypeFormation existingTypeFormation = typeFormationService.getTypeFormationRepository().getReferenceById(id);
        if (existingTypeFormation == null)
            return ResponseEntity.notFound().build();
        existingTypeFormation.setCode(typeFormation.getCode());
        existingTypeFormation.setLibelle(typeFormation.getLibelle());
        TypeFormation updateTypeFormation = typeFormationService.updateTypeFormation(existingTypeFormation);
        return ResponseEntity.ok(updateTypeFormation);
    }

    @Operation(summary = "Supprimer un type formation")
    @DeleteMapping("/typeFormation/{id}")
    public void delete(@PathVariable int id){
         typeFormationService.delete(id);
    }
}
