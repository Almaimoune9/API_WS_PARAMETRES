package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.model.Vocation;
import com.sitanInfo.API_WS_PARAMETRES.repository.VocationRepository;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Data
public class VocationService {

    @Autowired
    private VocationRepository vocationRepository;

    public ResponseWrapper<String> createVocation(Vocation vocation) {
        try {
            Vocation vocationExiste = vocationRepository.getByCode(vocation.getCode());

            if (vocationExiste != null) {
                return ResponseWrapper.ko("Cette vocation existe déjà.");
            } else {
                vocationRepository.save(vocation);
                return ResponseWrapper.ok("Vocation enregistrée avec succès.");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création de la vocation.");
        }
    }


//    public String creer(Vocation vocation) {
//        try {
//            Vocation vocationExiste = vocationRepository.getByCode(vocation.getCode());
//            if (vocationExiste != null){
//                return "Cette vocation existe deja";
//            } else {
//                vocationRepository.save(vocation);
//                return "Vocation enregistrer avec succés";
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est lors de la creation";
//        }
//    }


    public List<Vocation> lire() {
        return vocationRepository.findAll();
    }


    public Optional<Vocation> findById(Integer id) {
        return vocationRepository.findById(id);
    }


    public Vocation updateVocation(Vocation vocation){
        return vocationRepository.save(vocation);
    }
    
    public void supprimer(Integer id) {
        vocationRepository.deleteById(id);
    }
}
