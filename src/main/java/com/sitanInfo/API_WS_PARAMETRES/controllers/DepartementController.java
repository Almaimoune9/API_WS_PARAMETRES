package com.sitanInfo.API_WS_PARAMETRES.controllers;

import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.model.ExcelGenerator;
import com.sitanInfo.API_WS_PARAMETRES.services.DepartementService;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.FilterWrapper;
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
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @Operation(summary = "Créer un departement")
    @PostMapping("/departement")
    public ResponseWrapper<Departement> create(@RequestBody Departement departement){
        return departementService.create(departement);
    }

    @Operation(summary = "Afficher la liste des departement")
    @GetMapping("/departement")
    public List<Departement> read(){
        return departementService.read();
    }

    @Operation(summary = "Afficher un departement")
    @GetMapping("/departement/{id}")
    public Optional<Departement> departement(@PathVariable int id){
        return departementService.findById(id);
    }

    @Operation(summary = "Modifier un departement")
    @PutMapping("/departement/{id}")
    public ResponseEntity<Departement> updatedepartement(@PathVariable int id, @RequestBody Departement departement){
        Departement existingDepartment = departementService.getDepartementRepository().getById(id);
        if (existingDepartment == null)
            return ResponseEntity.notFound().build();
        existingDepartment.setDescription(departement.getDescription());
        existingDepartment.setNom(departement.getNom());
        existingDepartment.setTel(departement.getTel());
        existingDepartment.setCode(departement.getCode());
        Departement updatedepartement= departementService.updatedepartement(existingDepartment);
        return ResponseEntity.ok(updatedepartement);
    }

    @Operation(summary = "Supprimer un departement")
    @DeleteMapping("/departement/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
        Departement existeDepartement = departementService.getDepartementRepository().getById(id);
        if (existeDepartement == null)
            return ResponseEntity.notFound().build();
        departementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/search")
    public ResponseEntity search(@RequestBody FilterWrapper o){
        return ResponseEntity.ok(departementService.search(o));
    }

    /*@Operation(summary = "Exporter liste dep")
    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Departement> listOfStudents = departementService.read();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }*/

    @GetMapping("/export-to-excel")
    public void exportDepartmentsToExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=departments_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Departement> departementList = departementService.read();

        ExcelGenerator generator = new ExcelGenerator(departementList, "Liste Départements");
        generator.generateExcelFile(response);
    }


}
