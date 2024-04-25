package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Vocation;
import com.sitanInfo.API_WS_PARAMETRES.services.VocationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class VocationController {

    @Autowired
    private VocationService vocationService;

    @Operation(summary = "Créer une vocation")
    @PostMapping("/vocation")
    public String create(@RequestBody Vocation vocation){
        return vocationService.creer(vocation);
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
    public String update(@PathVariable int id, @RequestBody Vocation vocation){
        return vocationService.modifier(id, vocation);
    }

    @Operation(summary = "Supprimer une vocation")
    @DeleteMapping("/vocation/{id}")
    public String delete(@PathVariable int id){
        return vocationService.supprimer(id);
    }
}
