package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.GradeEnseigants;
import com.sitanInfo.API_WS_PARAMETRES.repository.GradeEnseignantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class GradeEnseigantService {

    @Autowired
    private GradeEnseignantRepository gradeEnseignantRepository;


    public String creer(GradeEnseigants gradeEnseigants) {
        try {
            GradeEnseigants gradeExiste = gradeEnseignantRepository.getByCode(gradeEnseigants.getCode());
            if (gradeExiste != null){
                return "Cette grade existe";
            } else {
                gradeEnseignantRepository.save(gradeEnseigants);
                return "Grade créer";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue lors de la création du grade";
        }
    }

    public List<GradeEnseigants> lire() {
        return gradeEnseignantRepository.findAll();
    }

    public Optional<GradeEnseigants> findByid(Integer id) {
        return gradeEnseignantRepository.findById(id);
    }

    public String supprimer(Integer id) {
        gradeEnseignantRepository.deleteById(id);
        return "Grade non trouvée";
    }

    public String modifier(Integer id, GradeEnseigants gradeEnseigants) {
        try {
            GradeEnseigants gradeEnseigantsModifier = gradeEnseignantRepository.findById(id).orElse(null);
            if (gradeEnseigantsModifier == null){
                return "Grade non trouvé";
            }
            //Mettre à jour les données
            gradeEnseigantsModifier.setCode(gradeEnseigants.getCode());
            gradeEnseigantsModifier.setEtat(gradeEnseigants.getEtat());
            gradeEnseigantsModifier.setType(gradeEnseigants.getType());
            gradeEnseigantsModifier.setLibelle(gradeEnseigants.getLibelle());

            //Enregistrer les modifications
            gradeEnseignantRepository.save(gradeEnseigantsModifier);
            return "Grade modifier";
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite";
        }
    }
}
