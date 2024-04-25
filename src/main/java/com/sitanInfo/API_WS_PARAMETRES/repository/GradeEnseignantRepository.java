package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.GradeEnseigants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeEnseignantRepository extends JpaRepository<GradeEnseigants, Integer> {
    GradeEnseigants getByCode(String code);
}
