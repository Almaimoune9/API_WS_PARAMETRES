package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.ModeReglement;
import com.sitanInfo.API_WS_PARAMETRES.repository.ModeReglementRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class ModeReglementService {

    @Autowired
    private ModeReglementRepository modeReglementRepository;

    public String creer(ModeReglement modeReglement) {
        try {
            ModeReglement modeExiste = modeReglementRepository.getByLibelle(modeReglement.getLibelle());
            if (modeExiste != null) {
                return "Ce mode existe deja";
            } else {
                modeReglementRepository.save(modeReglement);
                return "Mode Reglement créer avec succés";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la création du mode.";
        }
    }

    public List<ModeReglement> lire() {
        return modeReglementRepository.findAll();
    }


    public Optional<ModeReglement> findById(Integer id) {
        return modeReglementRepository.findById(id);
    }

    public String modifier(Integer id, ModeReglement modeReglement) {
        try {
            //Recherche le mode par son id
            ModeReglement modeModifier = modeReglementRepository.findById(id).orElse(null);

            if (modeModifier == null) {
                return "mode non trouvé";
            }
            //Mettre à jour les informations
            modeModifier.setLibelle(modeReglement.getLibelle());

            //Enregistrer les modifications
            modeReglementRepository.save(modeModifier);

            return "Mode modifier  avec succés";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification du mode.";
        }
    }


    public String supprimer(Integer id) {
        if (modeReglementRepository.existsById(id)){
            modeReglementRepository.deleteById(id);
            return "Mode supprimer avec sucés";
        } else {
            return "Ce mode de reglement n'existe pas";
        }
    }
}
