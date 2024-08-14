/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.controller;

import com.pruebatecnicajava.devsu.exceptions.ErrorEnum;
import com.pruebatecnicajava.devsu.exceptions.Exceptions;
import com.pruebatecnicajava.devsu.service.ClienteService;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteCreateTemp;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteSecTemp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nancy Mejia
 */
@RestController
@RequestMapping(path = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor

public class ClienteController {
    
    private final ClienteService clienteService;


    @GetMapping()
    public List<ClienteSecTemp> listar() {
        var listadoClientes = clienteService.listClientes();
        if (listadoClientes.isEmpty()) {
            throw new Exceptions(ErrorEnum.CLIENTE_NOT_FOUND, HttpStatus.NO_CONTENT.value());
        }
        return listadoClientes;
    }

    @GetMapping(value = "/ver/{clienteId}")
    public ClienteSecTemp buscarPorId(@PathVariable Long clienteId) {
        return clienteService.buscarCliente(clienteId);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteSecTemp crear(@RequestBody ClienteCreateTemp clienteRequest) {
        return clienteService.crearCliente(clienteRequest);
    }

    @PatchMapping(value = "/{clienteId}")
    public ClienteSecTemp actualizar(@PathVariable Long clienteId,
                                @RequestBody ClienteCreateTemp clienteRequest)
    {
        return clienteService.actualizarCliente(clienteId, clienteRequest);
    }

    @DeleteMapping(value = "/{clienteId}")
    public void eliminar(@PathVariable Long clienteId) {
        clienteService.elimCliente(clienteId);
    }
}
