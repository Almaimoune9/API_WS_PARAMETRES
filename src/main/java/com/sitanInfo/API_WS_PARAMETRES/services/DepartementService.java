package com.sitanInfo.API_WS_PARAMETRES.services;
import com.sitanInfo.API_WS_PARAMETRES.repository.DepartementRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sitanInfo.API_WS_PARAMETRES.model.Departement;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class DepartementService{

    @Autowired
    private DepartementRepository departementRepository;


    public String creer(Departement departement) {
        try {
            Departement departementExiste = departementRepository.getByNom(departement.getNom());
            if (departementExiste != null){
                return "Ce departement existe deja";
            } else {
                departementRepository.save(departement);
                return "Departement créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue";
        }
    }


    public List<Departement> lire() {
        return departementRepository.findAll();
    }


    public Optional<Departement> findById(Integer id) {
        return departementRepository.findById(id);
    }


    public String modifier(Integer id, Departement departement) {
        try {
            //Recherche le departement par son id
            Departement departementModifier = departementRepository.findById(id).orElse(null);

            if (departementModifier == null) {
                return "Module non trouvé";
            }
            //Mettre à jour les informations du module
            departementModifier.setCode(departement.getCode());
            departementModifier.setNom(departement.getNom());
            departementModifier.setTel(departement.getTel());
            departementModifier.setDescription(departement.getDescription());


            //Enregistrer les modifications
            departementRepository.save(departementModifier);

            return "Departement modifier avec succés";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification du departement.";
        }
    }

    public String supprimer(Integer id) {
        if (departementRepository.existsById(id)){
            departementRepository.deleteById(id);
            return "Departement supprimer";
        } else return "Ce departement n'existe pas";
    }
}
