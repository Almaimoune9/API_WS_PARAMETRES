package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.*;
import com.sitanInfo.API_WS_PARAMETRES.services.BatimentService;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class BatimentController {

    @Autowired
    private BatimentService batimentService;

    @Operation(summary = "Ajouter un batiment")
    @PostMapping("/batiment")
    public ResponseWrapper<Batiment> create(@RequestBody Batiment batiment){
       return batimentService.create(batiment);
    }

    @Operation(summary = "La liste des batiments")
    @GetMapping("/batiment")
    public List<Batiment> read(){
        return batimentService.findAll();
    }

    @Operation(summary = "Afficher un batiment")
    @GetMapping("/batiment/{id}")
    public Optional<Batiment> batiment(@PathVariable int id){
        return batimentService.findById(id);
    }

    @Operation(summary = "Modifier un batiment")
    @PutMapping("/batiment/{id}")
    public ResponseEntity<Batiment> update(@PathVariable int id,@RequestBody Batiment batiment){
        Batiment existingBatiment = batimentService.getBatimentRepository().getById(id);
        if (existingBatiment == null)
            return ResponseEntity.notFound().build();
        existingBatiment.setCode(batiment.getCode());
        existingBatiment.setNom(batiment.getNom());
        existingBatiment.setDescription(batiment.getDescription());
        existingBatiment.setType(batiment.getType());
        Batiment updateBatiment= batimentService.updateBatiment(existingBatiment);
        return ResponseEntity.ok(updateBatiment);
    }

    @Operation(summary = "Supprimer un batiment")
    @DeleteMapping("/batiment/{id}")
    public void delete(@PathVariable int id){
         batimentService.deleteBatiment(id);
    }


    //Partie salles
    @Operation(summary = "Ajouter une salle")
    @PostMapping("/salle")
    public ResponseWrapper<String> create(@RequestBody CreateSalleRequest request){
        return batimentService.createSalle(request.getSalles());
    }

    @Operation(summary = "Afficher la liste des salles")
    @GetMapping("/salle")
    public List<Salles> sallesList(){
        return batimentService.read();
    }

    @Operation(summary = "Afficher une salle")
    @GetMapping("/salle/{id}")
    public Optional<Salles> pays(@PathVariable int id){
        return batimentService.findByIdSalle(id);
    }

    @Operation(summary = "Modifier une salle")
    @PutMapping("/salle/{id}")
    public ResponseEntity<Salles> updateSalle(@PathVariable int id, @RequestBody Salles salles){
        Salles existingSalle = batimentService.getSallesRepository().getById(id);
        if (existingSalle == null)
            return ResponseEntity.notFound().build();
        existingSalle.setCode(salles.getCode());
        existingSalle.setNom(salles.getNom());
        existingSalle.setCapacite(salles.getCapacite());
        Salles updateSalle= batimentService.updateSalles(existingSalle);
        return ResponseEntity.ok(updateSalle);
    }

    @Operation(summary = "Supprimer une salle")
    @DeleteMapping("/salle/{id}")
    public void deletePays(@PathVariable int id){
        batimentService.delete(id);
    }


    @GetMapping("/export-to-excel-batiment")
    public void exportBatimentToExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=batiments_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Batiment> batimentList = batimentService.findAll();

        ExcelGenerator generator = new ExcelGenerator(batimentList, "Liste batiment");
        generator.generateExcelFile(response);
    }

    @GetMapping("/export-to-excel-salle")
    public void exportSalleToExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=salles_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Salles> sallesList = batimentService.read();

        ExcelGenerator generator = new ExcelGenerator(sallesList, "Liste salles");
        generator.generateExcelFile(response);
    }

}
