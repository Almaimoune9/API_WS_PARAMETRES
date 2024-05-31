package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Monnaie;
import com.sitanInfo.API_WS_PARAMETRES.model.Pays;
import com.sitanInfo.API_WS_PARAMETRES.repository.MonnaieRepository;
import com.sitanInfo.API_WS_PARAMETRES.repository.PaysRepository;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Data
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private MonnaieRepository monnaieRepository;

    public ResponseWrapper<String> createPays(Pays pays) {
        try {
            Pays paysExiste = paysRepository.getByNom(pays.getNom());

            if (paysExiste != null) {
                return ResponseWrapper.ko("Ce pays existe déjà.");
            } else {
                paysRepository.save(pays);
                return ResponseWrapper.ok("Pays créer avec succes");
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du pays.");
        }
    }


    public List<Pays> read(){
        return paysRepository.findAll();
    }

    public Optional<Pays> optionalPays(Integer id){
        return paysRepository.findById(id);
    }

    public void deletePays(Integer id){
        paysRepository.deleteById(id);
    }

    public Pays updatePays(Pays pays){
        return paysRepository.save(pays);
    }


    //Partie Monnaie
    public ResponseWrapper<String> createMonnaie(Monnaie monnaie) {
        try {
            Monnaie monnaieExiste = monnaieRepository.getByCode(monnaie.getCode());

            if (monnaieExiste != null) {
                return ResponseWrapper.ko("Cette monnaie existe déjà.");
            } else {
                monnaieRepository.save(monnaie);
                return ResponseWrapper.ok("Monnaie créé avec succès.");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création de la monnaie.");
        }
    }

    public List<Monnaie> findAll(){
        return monnaieRepository.findAll();
    }

    public Optional<Monnaie> optionalMonnaie(Integer id){
        return monnaieRepository.findById(id);
    }

    public Monnaie updateMonnaie(Monnaie monnaie){
       return monnaieRepository.save(monnaie);
    }
    public void delete(Integer id){
         monnaieRepository.deleteById(id);
    }
}
