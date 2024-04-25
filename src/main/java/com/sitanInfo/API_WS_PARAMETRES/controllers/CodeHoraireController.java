package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.CodeHoraire;
import com.sitanInfo.API_WS_PARAMETRES.model.Etablissement;
import com.sitanInfo.API_WS_PARAMETRES.services.CodeHoraireService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CodeHoraireController {

    @Autowired
    private CodeHoraireService codeHoraireService;

    @Operation(summary = "Ajouter un code horaire")
    @PostMapping("/codeHoraire")
    public String creer(@RequestBody CodeHoraire codeHoraire){
        return codeHoraireService.creer(codeHoraire);
    }

    @Operation(summary = "Afficher la liste des codes horaires")
    @GetMapping("/codeHoraire")
    public List<CodeHoraire> read(){
        return codeHoraireService.lire();
    }

    @Operation(summary = "Modifier un code horaire")
    @PutMapping("/codeHoraire/{id}")
    public String update(@PathVariable int id, @RequestBody CodeHoraire codeHoraire){
        return codeHoraireService.modifier(id, codeHoraire);
    }

    @Operation(summary = "Afficher un code horaire")
    @GetMapping("/codeHoraire/{id}")
    public Optional<CodeHoraire> codeHoraire(@PathVariable int id){
        return codeHoraireService.findByid(id);
    }

    @Operation(summary = "Supprimer un code horaire")
    @DeleteMapping("/codeHoraire/{id}")
    public String delete(@PathVariable int id){
        return codeHoraireService.supprimer(id);
    }
}
