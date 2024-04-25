package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.repository.BatimentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class BatimentService {

    @Autowired
    private  BatimentRepository batimentRepository;

    public String creer(Batiment batiment){
        try {
            Batiment batimentExiste = batimentRepository.getByNom(batiment.getNom());
            if (batimentExiste != null){
                return "Ce batiment existe deja";
            } else {
                batimentRepository.save(batiment);
                return "Etablissement créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenu lors de la cration";
        }
    }

    public List<Batiment> Lire(){
       return batimentRepository.findAll();
    }

    public Optional<Batiment> optionalBatiment(Integer id){
        return batimentRepository.findById(id);
    }

    public String supprimer(Integer id){
        if (batimentRepository.existsById(id)){
            batimentRepository.deleteById(id);
            return "Batiment supprimé";
        } else {
            return "Ce batiment n'existe pas";
        }
    }

    public String modifier(Integer id, Batiment batiment){
        try {
            Batiment batimentModifier = batimentRepository.findById(id).orElse(null);
                if (batimentModifier == null){
                    return "Batiment non trouvé";
                }
                //Mettre à jour les données
                batimentModifier.setCode(batiment.getCode());
                batimentModifier.setNom(batiment.getNom());
                batimentModifier.setType(batiment.getType());
                batimentModifier.setDescription(batiment.getDescription());
                batimentModifier.setCreateDate(batiment.getCreateDate());
                batimentModifier.setUpdateDate(batiment.getUpdateDate());

                //Enregistrer les données
                batimentRepository.save(batimentModifier);
                return "Batiment modifié";
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite lors de la modification";
        }
    }
}
