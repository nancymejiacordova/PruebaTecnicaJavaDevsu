/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.serviceImpl;

import com.pruebatecnicajava.devsu.exceptions.ErrorEnum;
import com.pruebatecnicajava.devsu.exceptions.Exceptions;
import com.pruebatecnicajava.devsu.model.Cliente;
import com.pruebatecnicajava.devsu.repository.ClienteRepository;
import com.pruebatecnicajava.devsu.restclient.RestCliente;
import com.pruebatecnicajava.devsu.service.ClienteService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nancy Mejia
 */
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{

    private final RestCliente restClient;
    private final ClienteRepository clienteRepository;
    @Override
    @Transactional(readOnly = true)
    public Cliente buscarCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
            .or(() -> this.buscarClienteMs(clienteId))
            .orElseThrow(() -> new Exceptions(ErrorEnum.CREAR_CUENTA_CLIENTE_NOT_FOUND));
    }
    @Transactional()
    public Optional<Cliente> buscarClienteMs(Long clienteId) {
        var cliente = this.restClient.buscarClientePorId(clienteId);
        var clienteEntity = Cliente.builder()
            . clienteId(cliente.getClienteId())
            .nombre(cliente.getNombre())
            .build();
        this.clienteRepository.save(clienteEntity);
        return Optional.of(clienteEntity);
    }
    
}
