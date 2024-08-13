/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.exceptions;

/**
 *
 * @author Nancy Mejia
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

/**
 * Exceptions.
 */
@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage", "suppressed", "status", "errorEnum"})
public class Exceptions extends RuntimeException {
    private final int status;
    private final String code;
    private final ErrorEnum errorEnum;

    public Exceptions(final ErrorEnum enumError) {
        this(enumError, (Throwable)null);
    }

    public Exceptions(final ErrorEnum enumError, final Throwable cause) {
        this(enumError, 500, cause);
    }

    public Exceptions(final ErrorEnum enumError, final int httpStatus) {
        this(enumError, httpStatus, (Throwable)null);
    }

    public Exceptions(final ErrorEnum enumError, final int httpStatus, final Throwable cause) {
        super(enumError.getMessage(), cause);
        this.errorEnum = enumError;
        this.status = httpStatus;
        this.code = enumError.getCode();
    }
}

