package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.CodeHoraire;
import com.sitanInfo.API_WS_PARAMETRES.services.CodeHoraireService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class CodeHoraireController {

    @Autowired
    private CodeHoraireService codeHoraireService;

    @Operation(summary = "Ajouter un code horaire")
    @PostMapping("/codeHoraire")
    public ResponseEntity<CodeHoraire> create(@RequestBody CodeHoraire codeHoraire){
        return codeHoraireService.create(codeHoraire);
    }

    @Operation(summary = "Afficher la liste des codes horaires")
    @GetMapping("/codeHoraire")
    public List<CodeHoraire> read(){
        return codeHoraireService.lire();
    }

    @Operation(summary = "Modifier un code horaire")
    @PutMapping("/codeHoraire/{id}")
    public ResponseEntity<CodeHoraire> update(@PathVariable int id, @RequestBody CodeHoraire codeHoraire){
        CodeHoraire existingcodeHoraire = codeHoraireService.getCodeHoraireRepository().getReferenceById(id);
        if (existingcodeHoraire == null)
            return ResponseEntity.notFound().build();
        existingcodeHoraire.setCode(codeHoraire.getCode());
        existingcodeHoraire.setLibelle(codeHoraire.getLibelle());
        existingcodeHoraire.setSeuil(codeHoraire.getSeuil());
        CodeHoraire updateCodeHoraire = codeHoraireService.updateCodeHoraire(existingcodeHoraire);
        return ResponseEntity.ok(updateCodeHoraire);
    }

    @Operation(summary = "Afficher un code horaire")
    @GetMapping("/codeHoraire/{id}")
    public Optional<CodeHoraire> codeHoraire(@PathVariable int id){
        return codeHoraireService.findByid(id);
    }

    @Operation(summary = "Supprimer un code horaire")
    @DeleteMapping("/codeHoraire/{id}")
    public void delete(@PathVariable int id){
         codeHoraireService.delete(id);
    }
}
