package com.sitanInfo.API_WS_PARAMETRES.services;
import com.sitanInfo.API_WS_PARAMETRES.model.Comptes;
import com.sitanInfo.API_WS_PARAMETRES.repository.ComptesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class ComptesService{

    @Autowired
    private ComptesRepository comptesRepository;


    public String creer(Comptes comptes) {
        try {
            Comptes comptesExiste = comptesRepository.getByCode(comptes.getCode());
            if (comptesExiste != null) {
                return "Ce compte existe deja";
            } else {
                comptesRepository.save(comptes);
                return "Compte créer";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite lors de la création du compte";
        }
    }


    public List<Comptes> lire() {
        return comptesRepository.findAll();
    }


    public Optional<Comptes> findById(Integer id) {
        return comptesRepository.findById(id);
    }


    public String modifier(Integer id, Comptes comptes) {
        try {
            Comptes compteModifier = comptesRepository.findById(id).orElse(null);
            if (compteModifier == null){
                return "Compte non trouvé";
            }
            //Mettre à jour les informations
            compteModifier.setCode(comptes.getCode());
            compteModifier.setType(comptes.getType());
            compteModifier.setDescription(comptes.getDescription());
            compteModifier.setNumero(comptes.getNumero());

            //Enregistrer les modifications
            comptesRepository.save(compteModifier);
            return "Compte modifié";
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite lors de la modification";
        }
    }


    public String supprimer(Integer id) {
        if (comptesRepository.existsById(id)){
            comptesRepository.deleteById(id);
            return "Compte supprimé";
        } else {
            return "Ce compte n'existe pas";
        }
    }
}
