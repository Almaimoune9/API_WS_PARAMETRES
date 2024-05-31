package com.sitanInfo.API_WS_PARAMETRES.wrapper;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ResponseWrapper <T>{

    private boolean status;
    private String message;
    private T entite;


    public static ResponseWrapper ko(String message) {

        return new ResponseWrapper(false, message, null);
    }

    public static <T> ResponseWrapper<T> ok(T entite) {

        return new ResponseWrapper(true, null, entite);
    }

}
