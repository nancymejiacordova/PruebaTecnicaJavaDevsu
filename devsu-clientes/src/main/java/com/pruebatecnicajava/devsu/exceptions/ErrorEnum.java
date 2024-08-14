/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Nancy Mejia
 */

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    /*ERRORES GENERALES*/
    DEFAULT("500", "Error Generico"),
    REST_CLIENT("ERROR_CO_001", "Error al consumir servicio REST"),
    INVALID_ARGS("ERROR_CO_002", "Argumentos invalidos"),
    NOT_ALLOWED("ERROR_CO_003", "No permitido"),
    INVALID_BODY("ERROR_CO_004", "Cuerpo de llamada invalido"),
    NO_CONTENT("ERROR_CO_005", "Cuerpo de llamada invalido"),
    /*ERRORES DE DOMINIO - Cliente*/
    CLIENTE_NOT_FOUND("ERROR_CL_001", "Cliente no existe"),;

    private String code;
    private String message;
}
