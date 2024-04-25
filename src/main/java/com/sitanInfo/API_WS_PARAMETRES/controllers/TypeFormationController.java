package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeFormation;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import com.sitanInfo.API_WS_PARAMETRES.services.TypeFormationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class TypeFormationController {

    @Autowired
    private TypeFormationService typeFormationService;

    @Operation(summary = "Créer un type formation")
    @PostMapping("/typeformation")
    public String creer(@RequestBody TypeFormation typeFormation){
        return typeFormationService.creer(typeFormation);
    }

    @Operation(summary = "Afficher la liste des types formations")
    @GetMapping("/typeformation")
    public List<TypeFormation> read(){
        return typeFormationService.lire();
    }

    @Operation(summary = "Afficher un type formation")
    @GetMapping("/typeformation/{id}")
    public Optional<TypeFormation> typeFormation(@PathVariable int id){
        return typeFormationService.findById(id);
    }

    @Operation(summary = "Modifier un type formation")
    @PutMapping("/typeformation/{id}")
    public String update(@PathVariable int id, @RequestBody TypeFormation typeFormation){
        return typeFormationService.modifier(id, typeFormation);
    }

    @Operation(summary = "Supprimer un type formation")
    @DeleteMapping("/typeformation/{id}")
    public String delete(@PathVariable int id){
        return typeFormationService.supprimer(id);
    }
}
