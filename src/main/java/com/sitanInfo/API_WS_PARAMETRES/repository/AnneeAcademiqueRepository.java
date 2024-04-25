package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.AnneeAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnneeAcademiqueRepository extends JpaRepository<AnneeAcademique, Integer> {

}
