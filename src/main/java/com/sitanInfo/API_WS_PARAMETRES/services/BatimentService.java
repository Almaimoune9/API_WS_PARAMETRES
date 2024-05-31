package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.model.Departement;
import com.sitanInfo.API_WS_PARAMETRES.model.Salles;
import com.sitanInfo.API_WS_PARAMETRES.repository.BatimentRepository;
import com.sitanInfo.API_WS_PARAMETRES.repository.SallesRepository;
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
public class BatimentService {

    @Autowired
    private  BatimentRepository batimentRepository;

    @Autowired
    private SallesRepository sallesRepository;

//    public String creer(Batiment batiment){
//        try {
//            Batiment batimentExiste = batimentRepository.getByNom(batiment.getNom());
//            if (batimentExiste != null){
//                return "Ce batiment existe deja";
//            } else {
//                batimentRepository.save(batiment);
//                return "Etablissement créer";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenu lors de la cration";
//        }
//    }

    public ResponseWrapper<Batiment> create(Batiment batiment) {
        try {
            Batiment batimentExiste = batimentRepository.getByNom(batiment.getNom());
            if (batimentExiste != null) {
                return ResponseWrapper.ko("Ce batiment existe deja");
            } else {
                //Le nom du batiment en majuscule
                batiment.setNom(batiment.getNom().toUpperCase());

                batiment = batimentRepository.saveAndFlush(batiment);
                return ResponseWrapper.ok(batiment);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du bâtiment");
        }
    }

    public List<Batiment> findAll(){
        try {
            return batimentRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

//    public List<Batiment> findAll(){
//        try {
//            return batimentRepository.findAll();
//        }catch (Exception e){
//            log.error();
//        }
//    }
    public Optional<Batiment> optionalBatiment(Integer id){
        return batimentRepository.findById(id);
    }

    public Optional<Batiment> findById(Integer id){
        try {
            Optional<Batiment> client = Optional.ofNullable(id).flatMap(batimentRepository::findById);
            return client;
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public void deleteBatiment(Integer id){
        batimentRepository.deleteById(id);
    }

    public Batiment updateBatiment(Batiment batiment){
        return batimentRepository.save(batiment);
    }

    //Partie salles
    public ResponseWrapper<String> createSalle(Salles salles) {
        try {
            Salles salleExiste = sallesRepository.getByCode(salles.getCode());

            if (salleExiste != null) {
                return ResponseWrapper.ko("Cette salle existe déjà.");
            } else {
                sallesRepository.save(salles);
                return ResponseWrapper.ok("Salle créer avec succes");
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création de la salle.");
        }
    }

    public List<Salles> read() {
        return sallesRepository.findAll();
    }


    public Optional<Salles> findByIdSalle(Integer id) {
        return sallesRepository.findById(id);
    }


    public Salles updateSalles(Salles salles){
        return sallesRepository.save(salles);
    }

    public void delete(Integer id) {
        sallesRepository.deleteById(id);
    }
}
