package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.repository.RolesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public String creer(Roles roles) {
        try {
            Roles roleExiste = rolesRepository.getByCode(roles.getCode());
            if (roleExiste != null){
                return "Ce role existe deja";
            } else {
                rolesRepository.save(roles);
                return "Role créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue";
        }
    }


    public List<Roles> lire() {
        return rolesRepository.findAll();
    }


    public Optional<Roles> findById(Integer id) {
        return rolesRepository.findById(id);
    }


    public String modifier(Integer id, Roles roles) {
        try {
            //Recherche le role par son id
            Roles roleModifier = rolesRepository.findById(id).orElse(null);

            if (roleModifier == null) {
                return "Role non trouvé";
            }
            //Mettre à jour les informations du role
            roleModifier.setCode(roles.getCode());
            roleModifier.setLibelle(roles.getLibelle());


            //Enregistrer les modifications
            rolesRepository.save(roleModifier);

            return "Role modifier avec succés";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification du role.";
        }
    }

    public String supprimer(Integer id) {
        if (rolesRepository.existsById(id)){
            rolesRepository.deleteById(id);
            return "Role supprimer";
        } else return "Ce role n'existe pas";
    }
}
