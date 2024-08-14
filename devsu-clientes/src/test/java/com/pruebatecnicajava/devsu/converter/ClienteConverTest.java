/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.converter;

import com.pruebatecnicajava.devsu.cliente.config.cliente.data.ClientData;
import com.pruebatecnicajava.devsu.utilities.convert.ClienteConverTemplate;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteSecTemp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Nancy Mejia
 */
public class ClienteConverTest {
    @InjectMocks
    private ClienteConverTemplate clienteConverTemplate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void convert() {
        var clientEntity = ClientData.getClient();
        var clientTemp = clienteConverTemplate.convert(clientEntity);
        Assertions.assertNotNull(clientTemp);
        Assertions.assertInstanceOf(ClienteSecTemp.class, clientTemp);
    }
}
