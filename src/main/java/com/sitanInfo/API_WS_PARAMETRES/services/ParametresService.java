package com.sitanInfo.API_WS_PARAMETRES.services;


import com.sitanInfo.API_WS_PARAMETRES.model.Parametres;
import com.sitanInfo.API_WS_PARAMETRES.repository.ParametresRepository;

import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Data
public class ParametresService {



    @Autowired
    private ParametresRepository parametresRepository;

//    public String creer(Parametres parametres){
//        try {
//            Parametres parametresExiste = parametresRepository.getByNom(parametres.getNom());
//            if (parametresExiste != null){
//                return "Ce Parametres existe deja";
//            } else {
//                parametresRepository.save(parametres);
//                return "Parametres créer";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenu lors de la cration";
//        }
//    }

    public ResponseWrapper<Parametres> create(Parametres parametres) {
        try {
            Parametres parametresExiste = parametresRepository.getByNom(parametres.getNom());
            if (parametresExiste != null) {
                return ResponseWrapper.ko("Ce parametres existe deja");
            } else {
                //Le nom du parmetres en majuscule
                parametres.setNom(parametres.getNom().toUpperCase());

                parametres = parametresRepository.saveAndFlush(parametres);
                return ResponseWrapper.ok(parametres);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du parametres");
        }
    }

//    public List<Parametres> Lire(){
//        return parametresRepository.findAll();
//    }


    public List<Parametres> findAll(){
        try {
            return parametresRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
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
