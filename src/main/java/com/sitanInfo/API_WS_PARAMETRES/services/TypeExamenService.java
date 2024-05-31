package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.StatutEtudiant;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeExamen;
import com.sitanInfo.API_WS_PARAMETRES.repository.TypeExamenRepository;
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
public class TypeExamenService {

    @Autowired
    private TypeExamenRepository typeExamenRepository;


    public ResponseWrapper<TypeExamen> create(TypeExamen typeExamen) {
        try {
            TypeExamen typeExamenExiste = typeExamenRepository.getByCode(typeExamen.getCode());
            if (typeExamenExiste != null) {
                return ResponseWrapper.ko("Ce type existe deja");
            } else {
                //Le code du type en majuscule
                typeExamen.setCode(typeExamen.getCode().toUpperCase());

                typeExamen = typeExamenRepository.saveAndFlush(typeExamen);
                return ResponseWrapper.ok(typeExamen);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du type");
        }
    }

    public List<TypeExamen> findAll(){
        try {
            return typeExamenRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }


    public Optional<TypeExamen> findById(Integer id) {
        return typeExamenRepository.findById(id);
    }

    public TypeExamen updateTypeExamen(TypeExamen typeExamen){
        return typeExamenRepository.save(typeExamen);
    }

    public void delete(Integer id) {
        typeExamenRepository.deleteById(id);
    }
}
