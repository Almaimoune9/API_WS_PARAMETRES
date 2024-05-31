package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.CreatePaysRequest;
import com.sitanInfo.API_WS_PARAMETRES.model.Monnaie;
import com.sitanInfo.API_WS_PARAMETRES.model.Pays;
import com.sitanInfo.API_WS_PARAMETRES.services.PaysService;
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
public class PaysController {
    @Autowired
    private PaysService paysService;

    @Operation(summary = "Créer un pays")
    @PostMapping("/pays")
    public ResponseWrapper<String> create(@RequestBody CreatePaysRequest request){
        return paysService.createPays(request.getPays());
    }

    @Operation(summary = "Afficher la liste des pays")
    @GetMapping("/pays")
    public List<Pays> readPays(){
        return paysService.read();
    }

    @Operation(summary = "Afficher un pays")
    @GetMapping("/pays/{id}")
    public Optional<Pays> pays(@PathVariable int id){
        return paysService.optionalPays(id);
    }

    @Operation(summary = "Modifier un pays")
    @PutMapping("/pays/{id}")
    public ResponseEntity<Pays> updatePays(@PathVariable int id, @RequestBody Pays pays){
        Pays existingPays = paysService.getPaysRepository().getById(id);
        if (existingPays == null)
            return ResponseEntity.notFound().build();
        existingPays.setCode(pays.getCode());
        existingPays.setNom(pays.getNom());
        existingPays.setDevise(pays.getDevise());
        Pays updatePays= paysService.updatePays(existingPays);
        return ResponseEntity.ok(updatePays);
    }

    @Operation(summary = "Supprimer un pays")
    @DeleteMapping("/pays/{id}")
    public void deletePays(@PathVariable int id){
        paysService.deletePays(id);
    }

    //Partie monnaie
    @Operation(summary = "Créer une monnaie")
    @PostMapping("/monnaie")
    public ResponseWrapper<String> create(@RequestBody Monnaie monnaie){
        return paysService.createMonnaie(monnaie);
    }

    @Operation(summary = "Afficher la liste des monnaies")
    @GetMapping("/monnaie")
    public List<Monnaie> read(){
        return paysService.findAll();
    }

    @Operation(summary = "Afficher une monnaie")
    @GetMapping("/monnaie/{id}")
    public Optional<Monnaie> monnaie(@PathVariable int id){
        return paysService.optionalMonnaie(id);
    }

    @Operation(summary = "Modifier une monnaie")
    @PutMapping("/monnaie/{id}")
    public ResponseEntity<Monnaie> updateMonnaie(@PathVariable int id, @RequestBody Monnaie monnaie){
        Monnaie existingMonnaie = paysService.getMonnaieRepository().getById(id);
        if (existingMonnaie == null)
            return ResponseEntity.notFound().build();
        existingMonnaie.setCode(monnaie.getCode());
        existingMonnaie.setLibelle(monnaie.getLibelle());
        existingMonnaie.setPrecision(monnaie.getPrecision());
        existingMonnaie.setTaux(monnaie.getTaux());
        Monnaie updateMonnaie= paysService.updateMonnaie(existingMonnaie);
        return ResponseEntity.ok(updateMonnaie);
    }

    @Operation(summary = "Supprimer une monnaie")
    @DeleteMapping("/monnaie/{id}")
    public void delete(@PathVariable int id){
        paysService.delete(id);
    }
}
