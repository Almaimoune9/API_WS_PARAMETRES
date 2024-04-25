package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.ModeReglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeReglementRepository extends JpaRepository<ModeReglement, Integer> {

    ModeReglement getByLibelle(String libelle);
}
