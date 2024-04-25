package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Vocation;
import com.sitanInfo.API_WS_PARAMETRES.repository.VocationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class VocationService {

    @Autowired
    private VocationRepository vocationRepository;

    public String creer(Vocation vocation) {
        try {
            Vocation vocationExiste = vocationRepository.getByCode(vocation.getCode());
            if (vocationExiste != null){
                return "Cette vocation existe deja";
            } else {
                vocationRepository.save(vocation);
                return "Vocation enregistrer avec succés";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur est lors de la creation";
        }
    }


    public List<Vocation> lire() {
        return vocationRepository.findAll();
    }


    public Optional<Vocation> findById(Integer id) {
        return vocationRepository.findById(id);
    }


    public String modifier(Integer id, Vocation vocation) {
        try {
            //Rechercher la vocation par son id
            Vocation vocationModifier = vocationRepository.findById(id).orElse(null);
            if (vocationModifier == null){
                return "Vocation non trouvée";
            }
            //Mettre à jour les données
            vocationModifier.setCode(vocation.getCode());
            vocationModifier.setEtat(vocation.getEtat());
            vocationModifier.setDescription(vocation.getDescription());
            vocationModifier.setLibelle(vocation.getLibelle());

            //Enregistrer les modifications
            vocationRepository.save(vocationModifier);
            return "Vocation modifiée avec succés";
        } catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue lors de la modification de la vocation";
        }
    }
    
    public String supprimer(Integer id) {
        if (vocationRepository.existsById(id)){
            vocationRepository.deleteById(id);
            return "Vocation supprmer";
        }else {
            return "Cette vocation n'existe pas";
        }
    }
}
