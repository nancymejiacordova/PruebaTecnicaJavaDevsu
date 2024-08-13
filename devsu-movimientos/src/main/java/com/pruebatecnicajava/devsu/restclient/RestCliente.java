/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.restclient;

import com.pruebatecnicajava.devsu.exceptions.ErrorEnum;
import com.pruebatecnicajava.devsu.exceptions.Exceptions;
import com.pruebatecnicajava.devsu.model.Cliente;
import com.pruebatecnicajava.devsu.templates.ClienteRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author Nancy Mejia
 */

@Component
@RequiredArgsConstructor
public class RestCliente {
    
    private final RestClient restClient;
    @Value("${rest.client.servicio.cliente.endpoint}")
    private String msClientesEndPoint;

    public ClienteRest buscarClientePorId(Long idcliente) {
        try {
            return this.restClient.get()
                .uri(this.msClientesEndPoint.concat("/ver/{idcliente}"), idcliente)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ClienteRest.class);
        } catch (RestClientException exc) {
            throw new Exceptions(ErrorEnum.ERROR_CONSULTA_CLIENTE, exc);
        }
    }
}
