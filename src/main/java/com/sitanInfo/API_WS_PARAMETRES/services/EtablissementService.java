package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Etablissement;
import com.sitanInfo.API_WS_PARAMETRES.repository.EtablissementRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class EtablissementService {
    @Autowired
    private  EtablissementRepository etablissementRepository;


    public String creer(Etablissement etablissement) {
        try {
            Etablissement etablissementExiste = etablissementRepository.getByNom(etablissement.getNom());
            if (etablissementExiste != null){
                return "Cet etablissement existe deja";
            } else {
                etablissementRepository.save(etablissement);
                return "Etablissement créer";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue lors de la création de l'etablissement";
        }
    }


    public List<Etablissement> lire() {
        return etablissementRepository.findAll();
    }


    public String modifier(Integer id, Etablissement etablissement) {
        try {
            Etablissement etablissementModifier = etablissementRepository.findById(id).orElse(null);
            if (etablissementModifier == null){
                return "Etablissement non trouvé";
            }
            //Mettre à jour les données
            etablissementModifier.setAbreviation(etablissement.getAbreviation());
            etablissementModifier.setCode(etablissement.getCode());
            etablissementModifier.setAdresse(etablissement.getAdresse());
            etablissementModifier.setGroupe(etablissement.getGroupe());
            etablissementModifier.setIdentifiantEtab(etablissement.getIdentifiantEtab());
            etablissementModifier.setPays(etablissement.getPays());
            etablissementModifier.setTel(etablissement.getTel());
            etablissementModifier.setVille(etablissement.getVille());
            etablissementModifier.setVille(etablissement.getVille());

            //Enregistrer les modifications
            etablissementRepository.save(etablissementModifier);
            return "Données modifiées";

        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite";
        }
    }


    public String supprimer(Integer id) {
        if (etablissementRepository.existsById(id)){
            etablissementRepository.deleteById(id);
            return "Etablissement supprimer";
        } else {
            return "Etablissmeent no trouvé ";
        }
    }


    public Optional<Etablissement> findById(Integer id) {
        return etablissementRepository.findById(id);
    }
}
