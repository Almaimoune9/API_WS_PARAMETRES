package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.Monnaie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonnaieRepository extends JpaRepository<Monnaie, Integer> {

    Monnaie getByCode(String code);

    Monnaie getByLibelle(String libelle);
}
