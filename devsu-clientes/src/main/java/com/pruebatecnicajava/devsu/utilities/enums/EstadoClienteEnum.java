/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.pruebatecnicajava.devsu.utilities.enums;

/**
 *
 * @author Nancy Mejia
 */
public enum EstadoClienteEnum {
    
    TRUE,FALSE;
    
    public static String getEstadoString(EstadoClienteEnum estado) {
        return TRUE.equals(estado) ? "True" : "False";
    }
}
