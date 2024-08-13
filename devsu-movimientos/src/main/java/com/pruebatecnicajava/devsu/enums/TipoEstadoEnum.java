/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Nancy Mejia
 */
@Getter
@AllArgsConstructor
public enum TipoEstadoEnum {
     T("True"),F("False");
    private final String descripcion;
    public boolean valueBoolean(){
        return T.descripcion.equals(this.getDescripcion());
    };
}
