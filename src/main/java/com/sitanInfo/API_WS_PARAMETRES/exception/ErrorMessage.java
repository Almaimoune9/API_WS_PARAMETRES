package com.sitanInfo.API_WS_PARAMETRES.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private Date timestemp;
    private String message;
    private String description;
}
