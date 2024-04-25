package com.sitanInfo.API_WS_PARAMETRES.repository;
import com.sitanInfo.API_WS_PARAMETRES.model.Vocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocationRepository extends JpaRepository<Vocation, Integer> {
    Vocation getByCode(String code);
}
