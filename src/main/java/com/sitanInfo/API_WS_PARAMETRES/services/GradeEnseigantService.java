package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.model.Etablissement;
import com.sitanInfo.API_WS_PARAMETRES.model.GradeEnseigants;
import com.sitanInfo.API_WS_PARAMETRES.repository.GradeEnseignantRepository;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Data
public class GradeEnseigantService {

    @Autowired
    private GradeEnseignantRepository gradeEnseignantRepository;


//    public String creer(GradeEnseigants gradeEnseigants) {
//        try {
//            GradeEnseigants gradeExiste = gradeEnseignantRepository.getByCode(gradeEnseigants.getCode());
//            if (gradeExiste != null){
//                return "Cette grade existe";
//            } else {
//                gradeEnseignantRepository.save(gradeEnseigants);
//                return "Grade créer";
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenue lors de la création du grade";
//        }
//    }

    public ResponseWrapper<GradeEnseigants> create(GradeEnseigants gradeEnseigants) {
        try {
            GradeEnseigants gradeEnseigantsExiste = gradeEnseignantRepository.getByCode(gradeEnseigants.getCode());
            if (gradeEnseigantsExiste != null) {
                return ResponseWrapper.ko("Cette grade existe deja");
            } else {
                //Le code du grade en majuscule
                gradeEnseigants.setCode(gradeEnseigants.getCode().toUpperCase());

                gradeEnseigants =  gradeEnseignantRepository.saveAndFlush(gradeEnseigants);
                return ResponseWrapper.ok(gradeEnseigants);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du grade");
        }
    }

//    public List<GradeEnseigants> lire() {
//        return gradeEnseignantRepository.findAll();
//    }

    public List<GradeEnseigants> findAll(){
        try {
            return gradeEnseignantRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }


    public Optional<GradeEnseigants> findByid(Integer id) {
        return gradeEnseignantRepository.findById(id);
    }

    public GradeEnseigants updateGrade(GradeEnseigants gradeEnseigants){
        return gradeEnseignantRepository.save(gradeEnseigants);
    }

    public void delete(Integer id) {
        gradeEnseignantRepository.deleteById(id);
    }
}
