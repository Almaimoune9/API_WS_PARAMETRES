package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Parametres;
import com.sitanInfo.API_WS_PARAMETRES.model.Pays;
import com.sitanInfo.API_WS_PARAMETRES.repository.PaysRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public String creer(Pays pays) {
        try {
            Pays paysExiste = paysRepository.getByNom(pays.getNom());
            if (paysExiste != null){
                return "Cet pays existe deja";
            } else {
                paysRepository.save(pays);
                return "Pays créer";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue lors de la création du pays";
        }
    }

    public List<Pays> Lire(){
        return paysRepository.findAll();
    }

    public Optional<Pays> optionalParametres(Integer id){
        return paysRepository.findById(id);
    }

    public String supprimer(Integer id){
        if (paysRepository.existsById(id)){
            paysRepository.deleteById(id);
            return "Pays supprimé";
        } else {
            return "Ce pays n'existe pas";
        }
    }

    public String modifier(Integer id, Pays pays) {
        try {
            Pays paysModifier = paysRepository.findById(id).orElse(null);
            if (paysModifier == null){
                return "Pays non trouvé";
            }
            //Mettre à jour les données
            paysModifier.setCode(pays.getCode());
            paysModifier.setNom(pays.getNom());
            paysModifier.setDevise(pays.getDevise());


            //Enregistrer les modifications
            paysRepository.save(paysModifier);
            return "Données modifiées";

        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite";
        }
    }
}
