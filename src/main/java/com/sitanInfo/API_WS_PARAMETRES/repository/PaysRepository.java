package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysRepository extends JpaRepository<Pays, Integer> {

    Pays getByNom(String nom);
}
