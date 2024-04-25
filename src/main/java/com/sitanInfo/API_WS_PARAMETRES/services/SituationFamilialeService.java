package com.sitanInfo.API_WS_PARAMETRES.services;


import com.sitanInfo.API_WS_PARAMETRES.model.SituationFamiliale;
import com.sitanInfo.API_WS_PARAMETRES.repository.SituationFamilialeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class SituationFamilialeService {

    @Autowired
    private SituationFamilialeRepository situationFamilialeRepository;

    public String creer(SituationFamiliale situationFamiliale){
        situationFamilialeRepository.save(situationFamiliale);
        return "Situation familiale créer";
    }

    public List<SituationFamiliale> lire(){
        return situationFamilialeRepository.findAll();
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
