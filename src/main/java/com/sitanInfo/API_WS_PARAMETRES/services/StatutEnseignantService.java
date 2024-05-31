package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.model.StatutEnseignant;
import com.sitanInfo.API_WS_PARAMETRES.model.StatutEtudiant;
import com.sitanInfo.API_WS_PARAMETRES.repository.StatutEnseignantRepository;
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
public class StatutEnseignantService {

    @Autowired
    private StatutEnseignantRepository statutEnseignantRepository;

//    public String creer(StatutEnseignant  statutEnseignant) {
//        try {
//            StatutEnseignant statutExiste = statutEnseignantRepository.getByCode(statutEnseignant.getCode());
//            if (statutExiste != null){
//                return "Ce statut existe deja";
//            } else {
//                 statutEnseignantRepository.save(statutEnseignant);
//                return "Statut créer";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenue";
//        }
//    }

    public ResponseWrapper<StatutEnseignant> create(StatutEnseignant statutEnseignant) {
        try {
            StatutEnseignant statutEnseignantExiste = statutEnseignantRepository.getByCode(statutEnseignant.getCode());
            if (statutEnseignantExiste != null) {
                return ResponseWrapper.ko("Ce statut existe deja");
            } else {
                //Le code du statut en majuscule
                statutEnseignant.setCode(statutEnseignant.getCode().toUpperCase());

                statutEnseignant = statutEnseignantRepository.saveAndFlush(statutEnseignant);
                return ResponseWrapper.ok(statutEnseignant);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du statut");
        }
    }


    public List<StatutEnseignant> findAll(){
        try {
            return statutEnseignantRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    public List<StatutEnseignant> lire() {
        return statutEnseignantRepository.findAll();
    }


    public Optional<StatutEnseignant> findById(Integer id) {
        return  statutEnseignantRepository.findById(id);
    }


    public StatutEnseignant updateStatutEnseignant(StatutEnseignant statutEnseignant){
        return statutEnseignantRepository.save(statutEnseignant);
    }

    public void delete(Integer id) {
        statutEnseignantRepository.deleteById(id);
    }
}
