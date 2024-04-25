package com.sitanInfo.API_WS_PARAMETRES.controllers;
import com.sitanInfo.API_WS_PARAMETRES.model.GradeEnseigants;
import com.sitanInfo.API_WS_PARAMETRES.services.GradeEnseigantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class GradeEnseignantController {

    @Autowired
    private GradeEnseigantService gradeEnseigantService;

    @Operation(summary = "Créer une grade")
    @PostMapping("/grade")
    public String creer(@RequestBody GradeEnseigants gradeEnseigants){
        return gradeEnseigantService.creer(gradeEnseigants);
    }

    @Operation(summary = "Afficher la liste des grades")
    @GetMapping("/grade")
    public List<GradeEnseigants> read(){
        return gradeEnseigantService.lire();
    }

    @Operation(summary = "Afficher une grade")
    @GetMapping("/grade/{id}")
    public Optional<GradeEnseigants> grades(@PathVariable int id){
        return gradeEnseigantService.findByid(id);
    }

    @Operation(summary = "Modifier une grade")
    @PutMapping("/grade/{id}")
    public String update(@PathVariable int id, @RequestBody GradeEnseigants gradeEnseigants){
        return gradeEnseigantService.modifier(id, gradeEnseigants);
    }

    @Operation(summary = "Supprimer une grade")
    @DeleteMapping("/grade/{id}")
    public String delete(@PathVariable int id){
        return gradeEnseigantService.supprimer(id);
    }
}
