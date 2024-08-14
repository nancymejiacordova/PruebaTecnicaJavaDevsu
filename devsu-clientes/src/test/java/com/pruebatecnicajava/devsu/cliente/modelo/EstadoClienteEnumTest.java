/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.cliente.modelo;

import com.pruebatecnicajava.devsu.utilities.enums.EstadoClienteEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Nancy Mejia
 */
public class EstadoClienteEnumTest {
    
       @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void estadoString() {
        var resultado = EstadoClienteEnum.getEstadoString(EstadoClienteEnum.TRUE);
        Assertions.assertEquals("True", resultado);
    }

    @Test
    void estadoStringF() {
        var resultado = EstadoClienteEnum.getEstadoString(EstadoClienteEnum.FALSE);
        Assertions.assertEquals("False", resultado);
    }
    
}
