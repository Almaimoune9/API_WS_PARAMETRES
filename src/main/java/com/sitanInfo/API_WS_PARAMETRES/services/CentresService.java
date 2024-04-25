package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Centres;
import com.sitanInfo.API_WS_PARAMETRES.repository.CentresRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CentresService {

    @Autowired
    private CentresRepository centresRepository;

    public String creer(Centres centres){
        try {
            Centres centresExiste = centresRepository.getByNom(centres.getNom());
            if (centresExiste != null){
                return "Ce centre existe deja";
            } else {
                centresRepository.save(centres);
                return "Centre créer";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite";
        }
    }

    public List<Centres> lire(){
        return centresRepository.findAll();
    }

    public Optional<Centres> optionalCentres(Integer id){
        return centresRepository.findById(id);
    }

    public String supprimer(Integer id){
        if (centresRepository.existsById(id)){
            centresRepository.deleteById(id);
            return "Centre supprimé";
        } else {
            return "Ce centre n'existe pas";
        }
    }

    public String modifier(Integer id, Centres centres){
        try {
            Centres centresModifier = centresRepository.findById(id).orElse(null);
            if (centresModifier == null){
                return "Centre non trouvé";
            }
            //Mettre à jour les données
            centresModifier.setNom(centres.getNom());
            centresModifier.setCode(centres.getCode());
            centresModifier.setAdresse(centres.getAdresse());
            centresModifier.setTelephone(centres.getTelephone());

            //Enregistrer les modifications
            centresRepository.save(centresModifier);
            return "Centre modifier";
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite lors de la modification";
        }
    }
}
