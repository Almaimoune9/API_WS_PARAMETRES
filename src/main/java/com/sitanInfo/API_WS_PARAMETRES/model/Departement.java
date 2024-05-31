package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class Departement extends AbstractEntity implements ExcelExportable{


    private int id;
    private String code;
    private String nom;
    private String description;
    private String tel;

    @Override
    public String [] getHeaders() {
        return new String[] {"ID", "Code", "Nom", "Description", "Telephone"};
    }

    @Override
    public Object[] getData() {
        return new Object[] {id, code, nom, description, tel};
    }

}
