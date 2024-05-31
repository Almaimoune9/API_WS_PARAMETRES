package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeFormation;
import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import com.sitanInfo.API_WS_PARAMETRES.repository.TypeSalleRepository;
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
public class TypeSalleService {

    @Autowired
    private TypeSalleRepository typeSalleRepository;

//    public String creer(TypeSalle typeSalle) {
//        try {
//            TypeSalle typeExiste = typeSalleRepository.getByLib(typeSalle.getCode());
//            if (typeExiste != null){
//                return "Ce Type existe deja";
//            } else {
//                typeSalleRepository.save(typeSalle);
//                return "Type créer";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "Une erreur est survenue";
//        }
//    }


    public ResponseWrapper<TypeSalle> create(TypeSalle typeSalle) {
        try {
            TypeSalle typeSalleExiste = typeSalleRepository.getByLibelle(typeSalle.getLibelle());
            if (typeSalleExiste != null) {
                return ResponseWrapper.ko("Ce TypeSalle existe deja");
            } else {
                //Le libelle du type en majuscule
                typeSalle.setLibelle(typeSalle.getLibelle().toUpperCase());

                typeSalle = typeSalleRepository.saveAndFlush(typeSalle);
                return ResponseWrapper.ok(typeSalle);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du type salle");
        }
    }

    public List<TypeSalle> findAll(){
        try {
            return typeSalleRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
//    public List<TypeSalle> lire() {
//        return typeSalleRepository.findAll();
//    }

    public Optional<TypeSalle> findById(Integer id) {
        return typeSalleRepository.findById(id);
    }

    public TypeSalle updateTypeDSalle(TypeSalle typeSalle){
        return typeSalleRepository.save(typeSalle);
    }

    public void delete(Integer id){
        typeSalleRepository.deleteById(id);
    }
}
