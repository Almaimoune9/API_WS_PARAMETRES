package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeFormation;
import com.sitanInfo.API_WS_PARAMETRES.repository.TypeFormationRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class TypeFormationService {

    @Autowired
    private TypeFormationRepository typeFormationRepository;

    public String creer(TypeFormation typeFormation) {
        try {
            TypeFormation typeExiste = typeFormationRepository.getByCode(typeFormation.getCode());
            if (typeExiste != null){
                return "Ce Type existe deja";
            } else {
                typeFormationRepository.save(typeFormation);
                return "Type créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue";
        }
    }


    public List<TypeFormation> lire() {
        return typeFormationRepository.findAll();
    }

    public Optional<TypeFormation> findById(Integer id) {
        return typeFormationRepository.findById(id);
    }

    public String modifier(Integer id, TypeFormation typeFormation) {
        try {
            //Recherche le type par son id
            TypeFormation typeFormationModifier = typeFormationRepository.findById(id).orElse(null);

            if (typeFormationModifier == null) {
                return "Type non trouvé";
            }
            //Mettre à jour les informations du type
            typeFormationModifier.setCode(typeFormation.getCode());
            typeFormationModifier.setLibelle( typeFormation.getLibelle());


            //Enregistrer les modifications
            typeFormationRepository.save(typeFormationModifier);

            return "Type modifier avec succés";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification du type formation.";
        }
    }

    public String supprimer(Integer id) {
        if (typeFormationRepository.existsById(id)){
            typeFormationRepository.deleteById(id);
            return "Type formation supprimer";
        } else return "Ce type formation n'existe pas";
    }
}
