package com.sitanInfo.API_WS_PARAMETRES.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table( schema = "parametres")
@Data
public class Salles extends AbstractEntity implements ExcelExportable{

    private int id;
    private String code;
    private String nom;
    private int capacite;
    private int batiment_id = 0;



    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(value =ConstraintMode.NO_CONSTRAINT ),name = "batiment_id",referencedColumnName = "id", insertable = false, updatable = false)
    private Batiment batiment;

    @Override
    public String [] getHeaders(){
        return new String[] {"ID", "Code", "Nom","Capacite", "Batiment"};
    }

    @Override
    public Object[] getData(){
        return new Object[] {id, code, nom, capacite, batiment_id};
    }
}
