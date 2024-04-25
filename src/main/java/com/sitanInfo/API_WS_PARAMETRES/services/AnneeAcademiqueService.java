package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.AnneeAcademique;
import com.sitanInfo.API_WS_PARAMETRES.repository.AnneeAcademiqueRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class AnneeAcademiqueService {

    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;

    public AnneeAcademique creerAnneeAcademique(AnneeAcademique anneeAcademique) {
        //Verifier si  la date est en cours
        Date dateActuelle = new Date();
        try {
            if (anneeAcademique.getDateDeb().after(dateActuelle)) {
                //Verifie la durée entre les dates
                long differenceJours = Duration.between(anneeAcademique.getDateDeb().toInstant(), anneeAcademique.getDateFin().toInstant()).toDays();
                if (differenceJours <= 365) {
                    return anneeAcademiqueRepository.save(anneeAcademique);
                } else {
                    throw new IllegalArgumentException("La durée entre la dateDebut et la date de fin est maximum 365jours.");
                }
            } else {
                throw new IllegalArgumentException("La date de début doit être en cours.");
            }
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public List<AnneeAcademique> lire() {
        return anneeAcademiqueRepository.findAll();
    }

    public Optional<AnneeAcademique> findById(Integer id) {
        return anneeAcademiqueRepository.findById(id);
    }


    public String supprimer(Integer id) {
        if (anneeAcademiqueRepository.existsById(id)){
            anneeAcademiqueRepository.deleteById(id);
            return "Année academique supprimée";
        }else {
            return "Année academique non trouvée";
        }
    }
}
