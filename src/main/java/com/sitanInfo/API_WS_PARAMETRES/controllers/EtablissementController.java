package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Etablissement;
import com.sitanInfo.API_WS_PARAMETRES.model.ExcelGenerator;
import com.sitanInfo.API_WS_PARAMETRES.services.EtablissementService;
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
public class EtablissementController {

    @Autowired
    private EtablissementService etablissementService;

    @Operation(summary = "Créer un etablissement")
    @PostMapping("/etablissement")
    public ResponseWrapper<Etablissement> creer(@RequestBody Etablissement etablissement){
        return etablissementService.create(etablissement);
    }

    @Operation(summary = "Afficher la liste des etablissements")
    @GetMapping("/etablissement")
    public List<Etablissement>  read(){
        return etablissementService.findAll();
    }

    @Operation(summary = "Modifier un etablissement")
    @PutMapping("/etablissement/{id}")
    public ResponseEntity<Etablissement> updateEtablissement(@PathVariable int id, @RequestBody Etablissement etablissement){
        Etablissement existingEta = etablissementService.getEtablissementRepository().getById(id);
        if (existingEta == null)
            return ResponseEntity.notFound().build();
        existingEta.setCode(etablissement.getCode());
        existingEta.setNom(etablissement.getNom());
        Etablissement updateEtablissement= etablissementService.updateEtablissement(existingEta);
        return ResponseEntity.ok(updateEtablissement);
    }

    @Operation(summary = "Afficher un etablissement")
    @GetMapping("/etablissement/{id}")
    public Optional<Etablissement> etablissement(@PathVariable int id){
        return etablissementService.findById(id);
    }

    @Operation(summary = "Supprimer un etablissement")
    @DeleteMapping("/etablissement/{id}")
    public ResponseEntity<?> deleteEtablissement(@PathVariable int id){
        Etablissement existeEtablissement = etablissementService.getEtablissementRepository().getReferenceById(id);
        if (existeEtablissement == null)
            return ResponseEntity.notFound().build();
        etablissementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/export-to-excel-etablissement")
    public void exportEtablissementToExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=etablissements_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Etablissement> etablissementList = etablissementService.findAll();

        ExcelGenerator generator = new ExcelGenerator(etablissementList, "Liste etablissement");
        generator.generateExcelFile(response);
    }
}
