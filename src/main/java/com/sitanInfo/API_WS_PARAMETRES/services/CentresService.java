package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.model.Centres;
import com.sitanInfo.API_WS_PARAMETRES.repository.CentresRepository;
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
public class CentresService {

    @Autowired
    private CentresRepository centresRepository;

//    public String creer(Centres centres){
//        try {
//            Centres centresExiste = centresRepository.getByNom(centres.getNom());
//            if (centresExiste != null){
//                return "Ce centre existe deja";
//            } else {
//                centresRepository.save(centres);
//                return "Centre créer";
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur s'est produite";
//        }
//    }


    public ResponseWrapper<Centres> create(Centres centres) {
        try {
            Centres centresExiste = centresRepository.getByNom(centres.getNom());
            if (centresExiste != null) {
                return ResponseWrapper.ko("Ce centre existe deja");
            } else {
                //Le nom du centre en majuscule
                centres.setNom(centres.getNom().toUpperCase());

                centres = centresRepository.saveAndFlush(centres);
                return ResponseWrapper.ok(centres);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du centres");
        }
    }


    public List<Centres> findAll(){
        try {
            return centresRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

//    public List<Centres> lire(){
//        return centresRepository.findAll();
//    }

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
