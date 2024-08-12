/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.pruebatecnicajava.devsu.utilities.enums.GeneroEnum;

/**
 *
 * @author Nancy Mejia
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Persona {
    
    private String nombre;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;
    
    
    
    
}
