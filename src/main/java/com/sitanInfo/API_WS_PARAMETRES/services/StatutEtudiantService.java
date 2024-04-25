package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.StatutEtudiant;
import com.sitanInfo.API_WS_PARAMETRES.repository.StatutEtudiantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class StatutEtudiantService {

    @Autowired
    private StatutEtudiantRepository statutEtudiantRepository;

    public String creer(StatutEtudiant statutEtudiant) {
        try {
            StatutEtudiant statutExiste = statutEtudiantRepository.getByCode(statutEtudiant.getCode());
            if (statutExiste != null){
                return "Ce statut etudiant existe deja";
            } else {
                statutEtudiantRepository.save(statutEtudiant);
                return "Statut créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue";
        }
    }


    public List<StatutEtudiant> lire() {
        return statutEtudiantRepository.findAll();
    }


    public Optional<StatutEtudiant> findById(Integer id) {
        return  statutEtudiantRepository.findById(id);
    }


    public String modifier(Integer id, StatutEtudiant statutEtudiant) {
        try {
            //Recherche le statut par son id
            StatutEtudiant statutModifier =  statutEtudiantRepository.findById(id).orElse(null);

            if (statutModifier == null) {
                return "Statut etudiant non trouvé";
            }
            //Mettre à jour les informations du statut
            statutModifier.setCode(statutEtudiant.getCode());
            statutModifier.setLibelle(statutEtudiant.getLibelle());


            //Enregistrer les modifications
            statutEtudiantRepository.save(statutModifier);

            return "Statut modifier avec succés";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification du statut.";
        }
    }

    public String supprimer(Integer id) {
        if (statutEtudiantRepository.existsById(id)){
            statutEtudiantRepository.deleteById(id);
            return "Statut  supprimer";
        } else return "Ce statut n'existe pas";
    }
}
