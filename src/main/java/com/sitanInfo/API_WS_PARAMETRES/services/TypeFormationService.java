package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeFormation;
import com.sitanInfo.API_WS_PARAMETRES.repository.TypeFormationRepository;

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
public class TypeFormationService {

    @Autowired
    private TypeFormationRepository typeFormationRepository;

//    public String creer(TypeFormation typeFormation) {
//        try {
//            TypeFormation typeExiste = typeFormationRepository.getByCode(typeFormation.getCode());
//            if (typeExiste != null){
//                return "Ce Type existe deja";
//            } else {
//                typeFormationRepository.save(typeFormation);
//                return "Type créer";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenue";
//        }
//    }


    public ResponseWrapper<TypeFormation> create(TypeFormation typeFormation) {
        try {
            TypeFormation typeExiste = typeFormationRepository.getByCode(typeFormation.getCode());
            if (typeExiste != null) {
                return ResponseWrapper.ko("Ce type de formation existe déjà");
            } else {
                typeFormationRepository.save(typeFormation);
                return ResponseWrapper.ok(typeFormation);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du type de formation");
        }
    }


    public List<TypeFormation> findAll(){
        try {
            return typeFormationRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<TypeFormation> lire() {
        return typeFormationRepository.findAll();
    }

    public Optional<TypeFormation> findById(Integer id) {
        return typeFormationRepository.findById(id);
    }

    public TypeFormation updateTypeFormation(TypeFormation typeFormation){
        return typeFormationRepository.save(typeFormation);
    }

    public void delete(Integer id) {
        typeFormationRepository.deleteById(id);
    }
}
