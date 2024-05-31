package com.sitanInfo.API_WS_PARAMETRES.services;


import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.model.Centres;
import com.sitanInfo.API_WS_PARAMETRES.model.SituationFamiliale;
import com.sitanInfo.API_WS_PARAMETRES.repository.SituationFamilialeRepository;
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
public class SituationFamilialeService {

    @Autowired
    private SituationFamilialeRepository situationFamilialeRepository;

//    public String creer(SituationFamiliale situationFamiliale){
//        situationFamilialeRepository.save(situationFamiliale);
//        return "Situation familiale créer";
//    }


    public ResponseWrapper<SituationFamiliale> create(SituationFamiliale situationFamiliale) {
        try {
            SituationFamiliale situationFamilialeExiste = situationFamilialeRepository.getByCode(situationFamiliale.getCode());
            if (situationFamilialeExiste != null) {
                return ResponseWrapper.ko("Cette SituationFamiliale existe deja");
            } else {
                //Le code de la SituationFamiliale en majuscule
                situationFamiliale.setCode(situationFamiliale.getCode().toUpperCase());

                situationFamiliale = situationFamilialeRepository.saveAndFlush(situationFamiliale);
                return ResponseWrapper.ok(situationFamiliale);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création de la  SituationFamiliale");
        }
    }

//    public List<SituationFamiliale> lire(){
//        return situationFamilialeRepository.findAll();
//    }

    public List<SituationFamiliale> findAll(){
        try {
            return situationFamilialeRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Optional<SituationFamiliale> optionalSituationFamiliale(Integer id){
        return situationFamilialeRepository.findById(id);
    }

    public String supprimer(Integer id){
        if (situationFamilialeRepository.existsById(id)){
            situationFamilialeRepository.deleteById(id);
            return "Situation familiale supprimer";
        } else {
            return "Cette Situation familiale n'existe pas";
        }
    }

    public String modifier(Integer id, SituationFamiliale situationFamiliale){
        try {
            SituationFamiliale situationFamilialeModifier = situationFamilialeRepository.findById(id).orElse(null);
            if (situationFamilialeModifier == null){
                return "Situation familiale non trouvé";
            }
            //Mettre à jour les données
            situationFamilialeModifier.setCode(situationFamiliale.getCode());
            situationFamilialeModifier.setLibelle(situationFamiliale.getLibelle());

            //Enregistrer les modifications
            situationFamilialeRepository.save(situationFamilialeModifier);
            return "Situation familiale modifier";
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite";
        }
    }
}
