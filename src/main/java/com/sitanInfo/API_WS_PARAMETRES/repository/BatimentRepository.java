package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.Batiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatimentRepository extends JpaRepository<Batiment, Integer> {

    Batiment getByNom(String nom);
}
