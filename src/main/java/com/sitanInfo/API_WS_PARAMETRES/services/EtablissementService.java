package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.model.Etablissement;
import com.sitanInfo.API_WS_PARAMETRES.repository.EtablissementRepository;
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
public class EtablissementService {
    @Autowired
    private  EtablissementRepository etablissementRepository;


//    public String creer(Etablissement etablissement) {
//        try {
//            Etablissement etablissementExiste = etablissementRepository.getByNom(etablissement.getNom());
//            if (etablissementExiste != null){
//                return "Cet etablissement existe deja";
//            } else {
//                etablissementRepository.save(etablissement);
//                return "Etablissement créer";
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenue lors de la création de l'etablissement";
//        }
//    }


    public ResponseWrapper<Etablissement> create(Etablissement etablissement) {
        try {
            Etablissement etablissementExiste = etablissementRepository.getByNom(etablissement.getNom());
            if (etablissementExiste != null) {
                return ResponseWrapper.ko("Cet établissement existe déjà");
            } else {
                etablissementRepository.saveAndFlush(etablissement);
                return ResponseWrapper.ok(etablissement);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création de l'établissement");
        }
    }


    public List<Etablissement> findAll(){
        try {
            return etablissementRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    public List<Etablissement> lire() {
        return etablissementRepository.findAll();
    }


    public Etablissement updateEtablissement(Etablissement etablissement){
        return etablissementRepository.save(etablissement);
    }


    public void delete(Integer id){
        etablissementRepository.deleteById(id);
    }


    public Optional<Etablissement> findById(Integer id) {
        return etablissementRepository.findById(id);
    }
}
