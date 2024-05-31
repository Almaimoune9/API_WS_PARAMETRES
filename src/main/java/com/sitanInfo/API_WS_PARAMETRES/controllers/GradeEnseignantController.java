package com.sitanInfo.API_WS_PARAMETRES.controllers;
import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.model.ExcelGenerator;
import com.sitanInfo.API_WS_PARAMETRES.model.GradeEnseigants;
import com.sitanInfo.API_WS_PARAMETRES.services.GradeEnseigantService;
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
public class GradeEnseignantController {

    @Autowired
    private GradeEnseigantService gradeEnseigantService;

    @Operation(summary = "Créer une grade")
    @PostMapping("/grade")
    public ResponseWrapper<GradeEnseigants> create(@RequestBody GradeEnseigants gradeEnseigants){
        return gradeEnseigantService.create(gradeEnseigants);
    }

    @Operation(summary = "Afficher la liste des grades")
    @GetMapping("/grade")
    public List<GradeEnseigants> read(){
        return gradeEnseigantService.findAll();
    }

    @Operation(summary = "Afficher une grade")
    @GetMapping("/grade/{id}")
    public Optional<GradeEnseigants> grades(@PathVariable int id){
        return gradeEnseigantService.findByid(id);
    }

    @Operation(summary = "Modifier une grade")
    @PutMapping("/grade/{id}")
    public ResponseEntity<GradeEnseigants> updateGrade(@PathVariable int id, @RequestBody GradeEnseigants gradeEnseigants){
        GradeEnseigants existingGrades = gradeEnseigantService.getGradeEnseignantRepository().getById(id);
        if (existingGrades == null)
            return ResponseEntity.notFound().build();
        existingGrades.setCode(gradeEnseigants.getCode());
        existingGrades.setLibelle(gradeEnseigants.getLibelle());
        GradeEnseigants updateGrade= gradeEnseigantService.updateGrade(existingGrades);
        return ResponseEntity.ok(updateGrade);
    }

    @Operation(summary = "Supprimer une grade")
    @DeleteMapping("/grade/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable int id){
        GradeEnseigants existeGrade = gradeEnseigantService.getGradeEnseignantRepository().getById(id);
        if (existeGrade == null)
            return ResponseEntity.notFound().build();
        gradeEnseigantService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/export-to-excel-grade")
    public void exportGrdesToExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=grades_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<GradeEnseigants> gradeEnseigantsList = gradeEnseigantService.findAll();

        ExcelGenerator generator = new ExcelGenerator(gradeEnseigantsList, "Liste Grades");
        generator.generateExcelFile(response);
    }
}
