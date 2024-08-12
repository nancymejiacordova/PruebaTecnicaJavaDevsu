/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.utilities.templates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Nancy Mejia
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteSecTemp {
    
    private long edad;
    private long clienteId;
    private String nombre;
    private String identificacion;
    private String direccion;
    private String telefono; 
    private String genero;
    private String estado;
    
}
