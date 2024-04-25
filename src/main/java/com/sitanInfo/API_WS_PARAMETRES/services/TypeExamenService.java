package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeExamen;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeFormation;
import com.sitanInfo.API_WS_PARAMETRES.repository.TypeExamenRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class TypeExamenService {

    @Autowired
    private TypeExamenRepository typeExamenRepository;

    public String creer(TypeExamen typeExamen) {
        try {
            TypeExamen typeExiste = typeExamenRepository.getByCode(typeExamen.getCode());
            if (typeExiste != null){
                return "Ce Type examen existe deja";
            } else {
                typeExamenRepository.save(typeExamen);
                return "Type examen créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue";
        }
    }


    public List<TypeExamen> lire() {
        return typeExamenRepository.findAll();
    }

    public Optional<TypeExamen> findById(Integer id) {
        return typeExamenRepository.findById(id);
    }

    public String modifier(Integer id, TypeExamen typeExamen) {
        try {
            //Recherche le type par son id
            TypeExamen typeExamenModifier = typeExamenRepository.findById(id).orElse(null);

            if (typeExamenModifier == null) {
                return "Type non trouvé";
            }
            //Mettre à jour les informations du type
            typeExamenModifier.setCode(typeExamen.getCode());
            typeExamenModifier.setLibelle( typeExamen.getLibelle());


            //Enregistrer les modifications
            typeExamenRepository.save(typeExamenModifier);

            return "Type modifier avec succés";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification du type Examen.";
        }
    }

    public String supprimer(Integer id) {
        if (typeExamenRepository.existsById(id)){
            typeExamenRepository.deleteById(id);
            return "Type Examen supprimer";
        } else return "Ce type examen n'existe pas";
    }
}
