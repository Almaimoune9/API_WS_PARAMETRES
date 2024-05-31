package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import com.sitanInfo.API_WS_PARAMETRES.services.TypeSalleService;
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
public class TypeSalleController {

    @Autowired
    private TypeSalleService typeSalleService;

    @Operation(summary = "Créer un type salle")
    @PostMapping("/type")
    public ResponseWrapper<TypeSalle> create(@RequestBody TypeSalle typeSalle){
        return typeSalleService.create(typeSalle);
    }

    @Operation(summary = "Afficher la liste des types salles")
    @GetMapping("/type")
    public List<TypeSalle> read(){
        return typeSalleService.findAll();
    }

    @Operation(summary = "Afficher un type salle")
    @GetMapping("/type/{id}")
    public Optional<TypeSalle> typeSalle(@PathVariable int id){
        return typeSalleService.findById(id);
    }

    @Operation(summary = "Modifier un type salle")
    @PutMapping("/type/{id}")
    public ResponseEntity<TypeSalle> update(@PathVariable int id, @RequestBody TypeSalle typeSalle){
        TypeSalle existingtypeSalle = typeSalleService.getTypeSalleRepository().getReferenceById(id);
        if (existingtypeSalle == null)
            return ResponseEntity.notFound().build();
        existingtypeSalle.setCode(typeSalle.getCode());
        existingtypeSalle.setLibelle(typeSalle.getLibelle());
        existingtypeSalle.setDescription(typeSalle.getDescription());
        TypeSalle updateTypeSalle = typeSalleService.updateTypeDSalle(existingtypeSalle);
        return ResponseEntity.ok(updateTypeSalle);
    }

    @Operation(summary = "Supprimer un type salle")
    @DeleteMapping("/type/{id}")
    public void delete(@PathVariable int id){
         typeSalleService.delete(id);
    }
}
