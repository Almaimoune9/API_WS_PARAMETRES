package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import com.sitanInfo.API_WS_PARAMETRES.repository.TypeSalleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class TypeSalleService {

    @Autowired
    private TypeSalleRepository typeSalleRepository;

    public String creer(TypeSalle typeSalle) {
        try {
            TypeSalle typeExiste = typeSalleRepository.getByLib(typeSalle.getCode());
            if (typeExiste != null){
                return "Ce Type existe deja";
            } else {
                typeSalleRepository.save(typeSalle);
                return "Type créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue";
        }
    }


    public List<TypeSalle> lire() {
        return typeSalleRepository.findAll();
    }

    public Optional<TypeSalle> findById(Integer id) {
        return typeSalleRepository.findById(id);
    }

    public String modifier(Integer id, TypeSalle typeSalle) {
        try {
            //Recherche le role par son id
            TypeSalle typeSalleModifier = typeSalleRepository.findById(id).orElse(null);

            if (typeSalleModifier == null) {
                return "Type non trouvé";
            }
            //Mettre à jour les informations du type
            typeSalleModifier.setCode(typeSalle.getCode());
            typeSalleModifier.setDescription(typeSalle.getDescription());
            typeSalleModifier.setLib(typeSalle.getLib());


            //Enregistrer les modifications
            typeSalleRepository.save(typeSalleModifier);

            return "Type modifier avec succés";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification du type.";
        }
    }

    public String supprimer(Integer id) {
        if (typeSalleRepository.existsById(id)){
            typeSalleRepository.deleteById(id);
            return "Type supprimer";
        } else return "Ce type n'existe pas";
    }
}
