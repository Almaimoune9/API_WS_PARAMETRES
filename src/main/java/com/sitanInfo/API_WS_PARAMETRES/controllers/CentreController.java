package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Centres;
import com.sitanInfo.API_WS_PARAMETRES.services.CentresService;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CentreController {

    @Autowired
    private CentresService centresService;

    @Operation(summary = "Créer un centre")
    @PostMapping("/centres")
    public ResponseWrapper<Centres> creer(@RequestBody Centres centres){
        return centresService.create(centres);
    }

    @Operation(summary = "Afficher la liste des centres")
    @GetMapping("/centres")
    public List<Centres> read(){
        return centresService.findAll();
    }

    @Operation(summary = "Modifier un centre")
    @PutMapping("/centres/{id}")
    public String update(@PathVariable int id, @RequestBody Centres centres){
        return centresService.modifier(id, centres);
    }

    @Operation(summary = "Afficher un centre")
    @GetMapping("/centres/{id}")
    public Optional<Centres> centres(@PathVariable int id){
        return centresService.optionalCentres(id);
    }

    @Operation(summary = "Supprimer un centre")
    @DeleteMapping("/centres/{id}")
    public String delete(@PathVariable int id){
        return centresService.supprimer(id);
    }
}
