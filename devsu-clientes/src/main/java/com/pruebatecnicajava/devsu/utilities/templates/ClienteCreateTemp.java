/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.utilities.templates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pruebatecnicajava.devsu.utilities.enums.EstadoClienteEnum;
import com.pruebatecnicajava.devsu.utilities.enums.GeneroEnum;

/**
 *
 * @author Nancy Mejia
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateTemp {
    private String nombre;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String password;
  
    private int edad;
    private GeneroEnum genero;
    private EstadoClienteEnum estado;
}
