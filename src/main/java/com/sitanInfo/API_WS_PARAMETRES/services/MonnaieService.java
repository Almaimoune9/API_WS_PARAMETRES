package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.CodeHoraire;
import com.sitanInfo.API_WS_PARAMETRES.model.Monnaie;
import com.sitanInfo.API_WS_PARAMETRES.repository.MonnaieRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class MonnaieService {

    @Autowired
    private MonnaieRepository  monnaieRepository;

    public String creer(Monnaie monnaie){
        monnaieRepository.save(monnaie);
        return "Monnaie créer";
    }

    public List<Monnaie> lire() {
        return monnaieRepository.findAll();
    }

    public Optional<Monnaie> findByid(Integer id) {
        return monnaieRepository.findById(id);
    }

    public String supprimer(Integer id) {
        monnaieRepository.deleteById(id);
        return "Monnaie supprimé";
    }

    public String Modifier(Integer id, Monnaie monnaie){
        try {
            Monnaie monnaieModifier = monnaieRepository.findById(id).orElse(null);
            if (monnaieModifier == null){
                return "Monnaie non trouvé";
            }

            //Mettre à jour les données
            monnaieModifier.setCode(monnaie.getCode());
            monnaieModifier.setLibelle(monnaie.getLibelle());
            monnaieModifier.setTaux(monnaie.getTaux());
            monnaieModifier.setPrecision(monnaie.getPrecision());

            //Enregistrer les modifications
            monnaieRepository.save(monnaieModifier);
            return "Monnaie modifiée";
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreir s'est produite";
        }
    }
}
