package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.services.RolesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class RolesController {

    @Autowired
    private RolesService rolesService;


    @Operation(summary = "Créer un role")
    @PostMapping("/roles")
    public String creer(@RequestBody Roles roles){
        return rolesService.creer(roles);
    }

    @Operation(summary = "Afficher la liste des roles")
    @GetMapping("/roles")
    public List<Roles> read(){
        return rolesService.lire();
    }

    @Operation(summary = "Afficher un role")
    @GetMapping("/roles/{id}")
    public Optional<Roles> roles(@PathVariable int id){
        return rolesService.findById(id);
    }

    @Operation(summary = "Modifier un role")
    @PutMapping("/roles/{id}")
    public String update(@PathVariable int id, @RequestBody Roles roles){
        return rolesService.modifier(id, roles);
    }

    @Operation(summary = "Supprimer un role")
    @DeleteMapping("/roles/{id}")
    public String delete(@PathVariable int id){
        return rolesService.supprimer(id);
    }

}
