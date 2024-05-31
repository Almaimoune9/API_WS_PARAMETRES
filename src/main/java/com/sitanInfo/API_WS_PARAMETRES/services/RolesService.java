package com.sitanInfo.API_WS_PARAMETRES.services;


import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.repository.RolesRepository;
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
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

//    public String creer(Roles roles) {
//        try {
//            Roles roleExiste = rolesRepository.getByCode(roles.getCode());
//            if (roleExiste != null){
//                return "Ce role existe deja";
//            } else {
//                rolesRepository.save(roles);
//                return "Role créer";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenue";
//        }
//    }


    public ResponseWrapper<Roles> create(Roles roles) {
        try {
            Roles rolesExiste = rolesRepository.getByCode(roles.getCode());
            if (rolesExiste != null) {
                return ResponseWrapper.ko("Ce role existe deja");
            } else {
                //Le code du role en majuscule
                roles.setCode(roles.getCode().toUpperCase());

                roles = rolesRepository.saveAndFlush(roles);
                return ResponseWrapper.ok(roles);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du role");
        }
    }


    public List<Roles> findAll(){
        try {
            return rolesRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }


//    public List<Roles> lire() {
//        return rolesRepository.findAll();
//    }


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
    public Roles updateRoles(Roles roles){
        return rolesRepository.save(roles);
    }

    public void supprimer(Integer id) {
        rolesRepository.deleteById(id);
    }
}
