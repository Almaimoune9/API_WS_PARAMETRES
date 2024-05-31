package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.AnneeAcademique;
import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.repository.AnneeAcademiqueRepository;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Data
public class AnneeAcademiqueService {

    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;

//    public AnneeAcademique creerAnneeAcademique(AnneeAcademique anneeAcademique) {
//        //Verifier si  la date est en cours
//        Date dateActuelle = new Date();
//        try {
//            if (anneeAcademique.getDateDeb().after(dateActuelle)) {
//                //Verifie la durée entre les dates
//                long differenceJours = Duration.between(anneeAcademique.getDateDeb().toInstant(), anneeAcademique.getDateFin().toInstant()).toDays();
//                if (differenceJours <= 365) {
//                    return anneeAcademiqueRepository.save(anneeAcademique);
//                } else {
//                    throw new IllegalArgumentException("La durée entre la dateDebut et la date de fin est maximum 365jours.");
//                }
//            } else {
//                throw new IllegalArgumentException("La date de début doit être en cours.");
//            }
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException(ex.getMessage());
//        }
//    }
public ResponseWrapper<AnneeAcademique> createAnneeAcademique(AnneeAcademique anneeAcademique) {
    try {
        Date dateActuelle = new Date();

        if (anneeAcademique.getDateDeb().after(dateActuelle)) {

            long differenceJours = Duration.between(anneeAcademique.getDateDeb().toInstant(), anneeAcademique.getDateFin().toInstant()).toDays();

            if (differenceJours <= 365) {

                return ResponseWrapper.ok(anneeAcademiqueRepository.save(anneeAcademique));
            } else {
                return ResponseWrapper.ko("La dure entre la date de debut et la date de fin ne doit  dépasse 365 jours.");
            }
        } else {
            return ResponseWrapper.ko("La date de debut doit être encours.");
        }
    } catch (Exception e) {
        log.error(e.getMessage(), e);
        return ResponseWrapper.ko("Une erreur est survenue lors de la création de l'année académique.");
    }
}


//    public List<AnneeAcademique> lire() {
//        return anneeAcademiqueRepository.findAll();
//    }
    public List<AnneeAcademique> findAll(){
    try {
        return anneeAcademiqueRepository.findAll();
    } catch (Exception e){
        log.error(e.getMessage(), e);
        return Collections.emptyList();
    }
}

    public Optional<AnneeAcademique> findById(Integer id) {
        return anneeAcademiqueRepository.findById(id);
    }

    public AnneeAcademique updateAnneeAcademique(AnneeAcademique anneeAcademique){
        return anneeAcademiqueRepository.save(anneeAcademique);
    }
    public void delete(Integer id) {
        anneeAcademiqueRepository.deleteById(id);
    }
}
