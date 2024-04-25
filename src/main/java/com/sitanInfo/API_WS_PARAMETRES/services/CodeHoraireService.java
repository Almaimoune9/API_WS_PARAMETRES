package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.CodeHoraire;
import com.sitanInfo.API_WS_PARAMETRES.repository.CodeHoraireRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CodeHoraireService {

    @Autowired
    private CodeHoraireRepository codeHoraireRepository;

    public String creer(CodeHoraire codeHoraire){
        codeHoraireRepository.save(codeHoraire);
        return "Code horaire créer";
    }

    public List<CodeHoraire> lire() {
        return codeHoraireRepository.findAll();
    }

    public Optional<CodeHoraire> findByid(Integer id) {
        return codeHoraireRepository.findById(id);
    }

    public String supprimer(Integer id) {
        codeHoraireRepository.deleteById(id);
        return "Code horaire non trouvée";
    }

    public String modifier(Integer id, CodeHoraire codeHoraire) {
        try {
            CodeHoraire codeHoraireModifier = codeHoraireRepository.findById(id).orElse(null);
            if (codeHoraireModifier == null){
                return "Code horaire non trouvé";
            }
            //Mettre à jour les données
            codeHoraireModifier.setCode(codeHoraire.getCode());
            codeHoraireModifier.setSeuil(codeHoraire.getSeuil());
            codeHoraireModifier.setLibelle(codeHoraire.getLibelle());

            //Enregistrer les modifications
            codeHoraireRepository.save(codeHoraireModifier);
            return "Code horaire modifier";
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur s'est produite";
        }
    }
}
