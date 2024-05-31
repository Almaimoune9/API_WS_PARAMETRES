package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.model.Vocation;
import com.sitanInfo.API_WS_PARAMETRES.services.VocationService;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class VocationController {

    @Autowired
    private VocationService vocationService;

    @Operation(summary = "Créer une vocation")
    @PostMapping("/vocation")
    public ResponseWrapper<String> create(@RequestBody Vocation vocation){
        return vocationService.createVocation(vocation);
    }

    @Operation(summary = "Afficher la liste des vocations")
    @GetMapping("/vocation")
    public List<Vocation> read(){
        return vocationService.lire();
    }

    @Operation(summary = "Afficher une vocation")
    @GetMapping("/vocation/{id}")
    public Optional<Vocation> module(@PathVariable int id){
        return vocationService.findById(id);
    }

    @Operation(summary = "Modifier une vocation")
    @PutMapping("/vocation/{id}")
    public ResponseEntity<Vocation> update(@PathVariable int id, @RequestBody Vocation vocation){
        Vocation existingVocation = vocationService.getVocationRepository().getById(id);
        if (existingVocation == null)
            return ResponseEntity.notFound().build();
        existingVocation.setCode(vocation.getCode());
        existingVocation.setLibelle(vocation.getLibelle());
        existingVocation.setDescription(vocation.getDescription());
        Vocation updateVocation= vocationService.updateVocation(existingVocation);
        return ResponseEntity.ok(updateVocation);
    }

    @Operation(summary = "Supprimer une vocation")
    @DeleteMapping("/vocation/{id}")
    public void delete(@PathVariable int id){
         vocationService.supprimer(id);
    }
}
