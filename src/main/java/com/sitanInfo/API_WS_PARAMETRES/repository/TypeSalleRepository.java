package com.sitanInfo.API_WS_PARAMETRES.repository;

import com.sitanInfo.API_WS_PARAMETRES.model.TypeSalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSalleRepository extends JpaRepository<TypeSalle, Integer> {

    TypeSalle getByLib(String lib);
}
