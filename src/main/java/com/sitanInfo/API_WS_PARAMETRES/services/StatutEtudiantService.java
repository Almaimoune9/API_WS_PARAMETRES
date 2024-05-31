package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Mention;
import com.sitanInfo.API_WS_PARAMETRES.model.StatutEtudiant;
import com.sitanInfo.API_WS_PARAMETRES.repository.MentionRepository;
import com.sitanInfo.API_WS_PARAMETRES.repository.StatutEtudiantRepository;
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
public class StatutEtudiantService {

    @Autowired
    private StatutEtudiantRepository statutEtudiantRepository;

    @Autowired
    private MentionRepository mentionRepository;

//    public String creer(StatutEtudiant statutEtudiant) {
//        try {
//            StatutEtudiant statutExiste = statutEtudiantRepository.getByCode(statutEtudiant.getCode());
//            if (statutExiste != null){
//                return "Ce statut etudiant existe deja";
//            } else {
//                statutEtudiantRepository.save(statutEtudiant);
//                return "Statut créer";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenue";
//        }
//    }


    public ResponseWrapper<StatutEtudiant> create(StatutEtudiant statutEtudiant) {
        try {
            StatutEtudiant statutExiste = statutEtudiantRepository.getByCode(statutEtudiant.getCode());
            if (statutExiste != null) {
                return ResponseWrapper.ko("Ce statut étudiant existe déjà");
            } else {
                statutEtudiantRepository.save(statutEtudiant);
                return ResponseWrapper.ok(statutEtudiant);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du statut étudiant");
        }
    }


    public List<StatutEtudiant> findAllSatutEtudiant(){
        try {
            return statutEtudiantRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Optional<StatutEtudiant> findById(Integer id) {
        return  statutEtudiantRepository.findById(id);
    }


    public StatutEtudiant updateStatutEtudiant(StatutEtudiant statutEtudiant){
        return statutEtudiantRepository.save(statutEtudiant);
    }

    public void delete(Integer id) {
        statutEtudiantRepository.deleteById(id);
    }


    /*  Partie Mention Service   */
    public ResponseWrapper<Mention> createMention(Mention mention) {
        try {
            Mention mentionExiste = mentionRepository.getByCode(mention.getCode());
            if (mentionExiste != null) {
                return ResponseWrapper.ko("Cette mention existe déjà");
            } else {
                mentionRepository.save(mention);
                return ResponseWrapper.ok(mention);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création de cette mention");
        }
    }

    public List<Mention> findAllMention(){
        try {
            return mentionRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Optional<Mention> findByIdMention(Integer id) {
        return  mentionRepository.findById(id);
    }

    public Mention updateMention(Mention mention){
        return mentionRepository.save(mention);
    }

    public void deleteMention(Integer id) {
        mentionRepository.deleteById(id);
    }

}
