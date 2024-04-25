package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.StatutEnseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutEnseignantRepository extends JpaRepository<StatutEnseignant, Integer> {

    StatutEnseignant getByCode(String code);
}
