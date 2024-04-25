package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Roles;
import com.sitanInfo.API_WS_PARAMETRES.model.StatutEnseignant;
import com.sitanInfo.API_WS_PARAMETRES.repository.StatutEnseignantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class StatutEnseignantService {

    @Autowired
    private StatutEnseignantRepository statutEnseignantRepository;

    public String creer(StatutEnseignant  statutEnseignant) {
        try {
            StatutEnseignant statutExiste = statutEnseignantRepository.getByCode(statutEnseignant.getCode());
            if (statutExiste != null){
                return "Ce statut existe deja";
            } else {
                 statutEnseignantRepository.save(statutEnseignant);
                return "Statut créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue";
        }
    }


    public List<StatutEnseignant> lire() {
        return statutEnseignantRepository.findAll();
    }


    public Optional<StatutEnseignant> findById(Integer id) {
        return  statutEnseignantRepository.findById(id);
    }


    public String modifier(Integer id, StatutEnseignant statutEnseignant) {
        try {
            //Recherche le statut par son id
            StatutEnseignant statutModifier =  statutEnseignantRepository.findById(id).orElse(null);

            if (statutModifier == null) {
                return "Statut enseignant non trouvé";
            }
            //Mettre à jour les informations du role
            statutModifier.setCode(statutEnseignant.getCode());
            statutModifier.setLibelle(statutEnseignant.getLibelle());


            //Enregistrer les modifications
            statutEnseignantRepository.save(statutModifier);

            return "Statut modifier avec succés";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification du statut.";
        }
    }

    public String supprimer(Integer id) {
        if (statutEnseignantRepository.existsById(id)){
            statutEnseignantRepository.deleteById(id);
            return "Statut  supprimer";
        } else return "Ce statut n'existe pas";
    }
}
