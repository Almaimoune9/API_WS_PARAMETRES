package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.services.BatimentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class BatimentController {

    @Autowired
    private BatimentService batimentService;

    @Operation(summary = "Ajouter un batiment")
    @PostMapping("/batiment")
    public String create(@RequestBody Batiment batiment){
       return batimentService.creer(batiment);
    }

    @Operation(summary = "La liste des batiments")
    @GetMapping("/batiment")
    public List<Batiment> read(){
        return batimentService.Lire();
    }

    @Operation(summary = "Afficher un batiment")
    @GetMapping("/batiment/{id}")
    public Optional<Batiment> batiment(@PathVariable int id){
        return batimentService.optionalBatiment(id);
    }

    @Operation(summary = "Modifier un batiment")
    @PutMapping("/batiment/{id}")
    public String update(@PathVariable int id, @RequestBody Batiment batiment){
        return batimentService.modifier(id, batiment);
    }

    @Operation(summary = "Supprimer un batiment")
    @DeleteMapping("/batiment/{id}")
    public String delete(@PathVariable int id){
        return batimentService.supprimer(id);
    }

}
