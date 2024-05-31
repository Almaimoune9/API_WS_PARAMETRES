package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( schema = "parametres")
@Data
public class GradeEnseigants extends AbstractEntity implements ExcelExportable{

    public enum Type{
        LMD,
        CLASSIQUE
    }
    private int id;
    private  String code;
    private String libelle;
    private Boolean etat;
    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Override
    public String [] getHeaders() {
        return new String[] {"ID", "Code", "Libelle", "Type"};
    }

    @Override
    public Object[] getData() {
        return new Object[] {id, code, libelle, type != null ? type.name() : null};
    }

}
