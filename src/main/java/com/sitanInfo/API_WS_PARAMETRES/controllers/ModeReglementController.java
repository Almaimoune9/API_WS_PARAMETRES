package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.ModeReglement;
import com.sitanInfo.API_WS_PARAMETRES.services.ModeReglementService;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ModeReglementController {

    @Autowired
    private ModeReglementService modeReglementService;

    @Operation(summary = "Créer un mode de reglement")
    @PostMapping("/modeReglement")
    public ResponseWrapper<ModeReglement> create(@RequestBody ModeReglement modeReglement){
        return modeReglementService.create(modeReglement);
    }

    @Operation(summary = "Afficher la liste des modeReglement")
    @GetMapping("/modeReglement")
    public List<ModeReglement> read(){
        return modeReglementService.findAll();
    }

    @Operation(summary = "Afficher un modeReglement")
    @GetMapping("/modeReglement/{id}")
    public Optional<ModeReglement> modeReglement(@PathVariable int id){
        return modeReglementService.findById(id);
    }

    @Operation(summary = "Modifier un modeReglement")
    @PutMapping("/modeReglement/{id}")
    public String update(@PathVariable int id, @RequestBody ModeReglement modeReglement){
        return modeReglementService.modifier(id, modeReglement);
    }

    @Operation(summary = "Supprimer un modeReglement")
    @DeleteMapping("/modeReglement/{id}")
    public String delete(@PathVariable int id){
        return modeReglementService.supprimer(id);
    }
}
