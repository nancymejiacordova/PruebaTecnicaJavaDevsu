/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.service.Impl;

import com.pruebatecnicajava.devsu.model.Cliente;
import com.pruebatecnicajava.devsu.repository.ClienteRepository;
import com.pruebatecnicajava.devsu.service.ClienteService;
import com.pruebatecnicajava.devsu.utilities.enums.EstadoClienteEnum;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteCreateTemp;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteSecTemp;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nancy Mejia
 */
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ConversionService convService;
    private static final Logger log = (Logger) LoggerFactory.getLogger(ClienteServiceImpl.class);
            
    @Override
    public List<ClienteSecTemp> listClientes() {
        return this.clienteRepository.findAll().stream().map(this::clienteConverTemplate).toList();
    }

    @Override
    public ClienteSecTemp buscarCliente(Long idCliente) {
    //log.debug("Informacion del Cliente{}",idCliente);
    var c=this.buscarXid(idCliente);
    return this.clienteConverTemplate(c);
    }

    @Override
    public ClienteSecTemp crearCliente(ClienteCreateTemp cliente) {
     var c = Cliente.builder()
                .nombre(cliente.getNombre())
                .genero(cliente.getGenero())
                .edad(cliente.getEdad())
                .identificacion(cliente.getIdentificacion())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .password(cliente.getPassword())
                .estado(cliente.getEstado())
                .build();
        this.clienteRepository.save(c);
        return this.clienteConverTemplate(c);    }

    @Override
    public ClienteSecTemp actualizarCliente(Long idCliente, ClienteCreateTemp cliente) {

        var c = this.buscarXid(idCliente);
        c.setNombre(Objects.requireNonNullElse(cliente.getNombre(), cliente.getNombre()));
        c.setGenero(Objects.requireNonNullElse(cliente.getGenero(), cliente.getGenero()));
        c.setEdad(Objects.requireNonNullElse(cliente.getEdad(), cliente.getEdad()));
        c.setIdentificacion(Objects.requireNonNullElse(cliente.getIdentificacion(), cliente.getIdentificacion()));
        c.setDireccion(Objects.requireNonNullElse(cliente.getDireccion(), cliente.getDireccion()));
        c.setTelefono(Objects.requireNonNullElse(cliente.getTelefono(), cliente.getTelefono()));
        c.setPassword(Objects.requireNonNullElse(cliente.getPassword(), cliente.getPassword()));
        this.clienteRepository.save(c);
        
        return this.clienteConverTemplate(c);    }

    @Override
    public void elimCliente(Long idCliente) {
       var c = this.buscarXid(idCliente);
        c.setEstado(EstadoClienteEnum.FALSE);
        this.clienteRepository.save(c);
    }
    
    private ClienteSecTemp clienteConverTemplate(Cliente cliente){
        return this.convService.convert(cliente, ClienteSecTemp.class);
    }
   
    private Cliente buscarXid(Long idCliente){
        return this.clienteRepository.findById(idCliente).orElseThrow();
        //implementar completa la funcion
    }
    
}
