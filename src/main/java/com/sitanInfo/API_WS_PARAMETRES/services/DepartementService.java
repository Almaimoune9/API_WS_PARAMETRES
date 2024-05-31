package com.sitanInfo.API_WS_PARAMETRES.services;
import com.sitanInfo.API_WS_PARAMETRES.repository.DepartementRepository;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.FilterWrapper;
import com.sitanInfo.API_WS_PARAMETRES.wrapper.ResponseWrapper;
import jakarta.persistence.EntityManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sitanInfo.API_WS_PARAMETRES.model.Departement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Data
public class DepartementService{

    private final EntityManager em;

    @Autowired
    private DepartementRepository departementRepository;


    public String creer(Departement departement) {
        try {
            Departement departementExiste = departementRepository.getByNom(departement.getNom());
            if (departementExiste != null){
                return "Ce departement existe deja";
            } else {
                departementRepository.save(departement);
                return "Departement créer";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Une erreur est survenue";
        }
    }
    public ResponseWrapper<Departement> create(Departement departement) {
        try {
            Departement departementExiste = departementRepository.getByNom(departement.getNom());
            if (departementExiste != null) {
                return ResponseWrapper.ko("Ce departement existe deja");
            } else {
                //Le nom du departement en majuscule
                departement.setNom(departement.getNom().toUpperCase());

                departement = departementRepository.saveAndFlush(departement);
                return ResponseWrapper.ok(departement);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko("Une erreur est survenue lors de la création du bâtiment");
        }
    }

    public List<Departement> read() {
        return departementRepository.findAll();
    }


    public Optional<Departement> findById(Integer id) {
        return departementRepository.findById(id);
    }


    public Departement updatedepartement(Departement departement){
        return departementRepository.save(departement);
    }

    public void delete(Integer id) {
        departementRepository.deleteById(id);
    }

    //Methode pour le filtre
    public List<Departement> search(FilterWrapper f){
        List<Departement> departements = new ArrayList<>();
        String hql= "";
        try {
            hql += "FROM Departement";
            if (f.getCode() != null && !f.getCode().equals("")){
                hql += " and o.code like '%" + f.getCode() + "%' ";
            }
            if (f.getTel() != null && !f.getTel().equals("")){
                hql += " and o.tel like '%" + f.getTel() + "%' ";
            }
            if (f.getNom() != null && !f.getNom().equals("")){
                hql += "and o.nom like '%" + f.getNom() + "%' ";
            }
            hql += " order by o.id desc";
            departements = (List<Departement>) em.createQuery(hql).getResultList();
            return departements;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
}
