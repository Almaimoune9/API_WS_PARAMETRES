package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.services.RolesService;
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
public class RolesController {

    @Autowired
    private RolesService rolesService;


    @Operation(summary = "Créer un role")
    @PostMapping("/roles")
    public ResponseWrapper<Roles> create(@RequestBody Roles roles){
        return rolesService.create(roles);
    }

    @Operation(summary = "Afficher la liste des roles")
    @GetMapping("/roles")
    public List<Roles> read(){
        return rolesService.findAll();
    }

    @Operation(summary = "Afficher un role")
    @GetMapping("/roles/{id}")
    public Optional<Roles> roles(@PathVariable int id){
        return rolesService.findById(id);
    }

    @Operation(summary = "Modifier un role")
    @PutMapping("/roles/{id}")
    public ResponseEntity<Roles> update(@PathVariable int id, @RequestBody Roles roles){
        Roles existingRoles= rolesService.getRolesRepository().getReferenceById(id);
        if (existingRoles == null)
            return ResponseEntity.notFound().build();
        existingRoles.setCode(roles.getCode());
        existingRoles.setLibelle(roles.getLibelle());
        Roles updateRoles = rolesService.updateRoles(existingRoles);
        return ResponseEntity.ok(updateRoles);
    }

    @Operation(summary = "Supprimer un role")
    @DeleteMapping("/roles/{id}")
    public void delete(@PathVariable int id){
         rolesService.supprimer(id);
    }

}
