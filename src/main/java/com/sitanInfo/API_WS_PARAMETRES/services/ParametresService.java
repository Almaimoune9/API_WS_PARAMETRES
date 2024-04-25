package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Etablissement;
import com.sitanInfo.API_WS_PARAMETRES.model.Parametres;
import com.sitanInfo.API_WS_PARAMETRES.repository.ParametresRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class ParametresService {

    @Autowired
    private ParametresRepository parametresRepository;

    public String creer(Parametres parametres){
        try {
            Parametres parametresExiste = parametresRepository.getByNom(parametres.getNom());
            if (parametresExiste != null){
                return "Ce Parametres existe deja";
            } else {
                parametresRepository.save(parametres);
                return "Parametres créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenu lors de la cration";
        }
    }

    public List<Parametres> Lire(){
        return parametresRepository.findAll();
    }

    public Optional<Parametres> optionalParametres(Integer id){
        return parametresRepository.findById(id);
    }

    public String supprimer(Integer id){
        if (parametresRepository.existsById(id)){
            parametresRepository.deleteById(id);
            return "Parametres supprimé";
        } else {
            return "Ce Parametres n'existe pas";
        }
    }

    public String modifier(Integer id, Parametres parametres) {
        try {
            Parametres parametresModifier = parametresRepository.findById(id).orElse(null);
            if (parametresModifier == null){
                return "Parametres non trouvé";
            }
            //Mettre à jour les données
            parametresModifier.setDescription(parametres.getDescription());
            parametresModifier.setNom(parametresModifier.getNom());
            parametresModifier.setValeur(parametresModifier.getValeur());
            parametresModifier.setFormat(parametresModifier.getFormat());

            //Enregistrer les modifications
            parametresRepository.save(parametresModifier);
            return "Données modifiées";

        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite";
        }
    }
}
