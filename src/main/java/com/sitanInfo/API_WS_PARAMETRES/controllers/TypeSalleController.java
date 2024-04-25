package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import com.sitanInfo.API_WS_PARAMETRES.services.TypeSalleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class TypeSalleController {

    @Autowired
    private TypeSalleService typeSalleService;

    @Operation(summary = "Créer un type salle")
    @PostMapping("/type")
    public String creer(@RequestBody TypeSalle typeSalle){
        return typeSalleService.creer(typeSalle);
    }

    @Operation(summary = "Afficher la liste des types salles")
    @GetMapping("/type")
    public List<TypeSalle> read(){
        return typeSalleService.lire();
    }

    @Operation(summary = "Afficher un type salle")
    @GetMapping("/type/{id}")
    public Optional<TypeSalle> typeSalle(@PathVariable int id){
        return typeSalleService.findById(id);
    }

    @Operation(summary = "Modifier un type salle")
    @PutMapping("/type/{id}")
    public String update(@PathVariable int id, @RequestBody TypeSalle typeSalle){
        return typeSalleService.modifier(id, typeSalle);
    }

    @Operation(summary = "Supprimer un type salle")
    @DeleteMapping("/type/{id}")
    public String delete(@PathVariable int id){
        return typeSalleService.supprimer(id);
    }
}
