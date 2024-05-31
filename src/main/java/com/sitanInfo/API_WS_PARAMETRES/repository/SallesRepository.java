package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.Salles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SallesRepository extends JpaRepository<Salles, Integer> {

    Salles getByCode(String code);
}
