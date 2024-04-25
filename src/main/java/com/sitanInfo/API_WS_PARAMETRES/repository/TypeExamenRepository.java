package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeExamenRepository extends JpaRepository<TypeExamen, Integer> {

    TypeExamen getByCode(String code);
}
