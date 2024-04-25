package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Etablissement;
import com.sitanInfo.API_WS_PARAMETRES.model.Parametres;
import com.sitanInfo.API_WS_PARAMETRES.services.ParametresService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ParametresController {

    @Autowired
    private ParametresService parametresService;

    @Operation(summary = "Ajouter un parametre")
    @PostMapping("/parametres")
    public String creer(@RequestBody Parametres parametres){
        return parametresService.creer(parametres);
    }

    @Operation(summary = "Afficher la liste des parametres")
    @GetMapping("/parametres")
    public List<Parametres> read(){
        return parametresService.Lire();
    }

    @Operation(summary = "Modifier un parametres")
    @PutMapping("/parametres/{id}")
    public String update(@PathVariable int id, @RequestBody Parametres parametres){
        return parametresService.modifier(id, parametres);
    }

    @Operation(summary = "Afficher un parametre")
    @GetMapping("/parametres/{id}")
    public Optional<Parametres> parametres(@PathVariable int id){
        return parametresService.optionalParametres(id);
    }

    @Operation(summary = "Supprimer un parametre")
    @DeleteMapping("/parametres/{id}")
    public String delete(@PathVariable int id){
        return parametresService.supprimer(id);
    }
}
