/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.repository.integracion.impl;

import com.pruebatecnicajava.devsu.enums.TipoEstadoEnum;
import com.pruebatecnicajava.devsu.model.Cliente;
import com.pruebatecnicajava.devsu.repository.ClienteRepository;
import com.pruebatecnicajava.devsu.repository.CuentaRepository;
import com.pruebatecnicajava.devsu.repository.integracion.ClienteIntegracionRepository;
import com.pruebatecnicajava.devsu.utilities.eventos.EventoGenerico;
import com.pruebatecnicajava.devsu.utilities.eventos.cliente.ClienteCreadoEv;
import com.pruebatecnicajava.devsu.utilities.eventos.cliente.ClienteElimEv;
import com.pruebatecnicajava.devsu.utilities.eventos.cliente.ClienteModEv;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nancy Mejia
 */
@Repository
@RequiredArgsConstructor
public class ClienteIntegracionRabbitRepositoryImpl implements ClienteIntegracionRepository {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteIntegracionRabbitRepositoryImpl.class);
    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentasRepository;
    @Override
    @Transactional
    @RabbitListener(queues = "devsu.clientes.queue")
    public void procesarEvento(EventoGenerico event){
        switch (event.getAccion()) {
            case "CLIENTE_CREADO": procesarClienteCreado((ClienteCreadoEv) event); break;
            case "CLIENTE_MODIFICADO": procesarClienteModificado((ClienteModEv) event); break;
            case "CLIENTE_ELIMINADO": procesarClienteEliminado((ClienteElimEv) event); break;
            default:
                LOGGER.warn("El evento que se ha emitido no es manejable por el servicio");
        }
    }

    private void procesarClienteCreado(ClienteCreadoEv clienteCreadoEvent) {
        try {
            LOGGER.info("Mensaje recivido {} creando cliente", clienteCreadoEvent);
            this.clienteRepository.save(Cliente.builder()
                .clienteId(clienteCreadoEvent.getClienteId())
                .nombre(clienteCreadoEvent.getNombre())
                .build());
            LOGGER.info("Evento de cliente creado procesado de menera correcta");
        } catch (Exception exc) {
            LOGGER.info("Se ha producido un error procesando el evento de creacion {0}.", exc);
        }
    }

    private void procesarClienteModificado(ClienteModEv clienteModificadoEvent) {
        try {
            LOGGER.info("Mensaje recivido {} modificando cliente.", clienteModificadoEvent);
            this.clienteRepository.save(Cliente.builder()
                .clienteId(clienteModificadoEvent.getClienteId())
                .nombre(clienteModificadoEvent.getNombre())
                .build());
            LOGGER.info("Evento de cliente modificado procesado de menera correcta");
        } catch (Exception exc) {
            LOGGER.info("Se ha producido un error procesando el evento de modificacion {0}.", exc);
        }
    }

    private void procesarClienteEliminado(ClienteElimEv clienteEliminadoEvent) {
        try {
            LOGGER.info("Mensaje recivido {} eliminando cliente ", clienteEliminadoEvent);
            LOGGER.info("Se cambia el estado de las cuentas relacionadas al cliente");
            var cliente = this.clienteRepository.findById(clienteEliminadoEvent.getClienteId())
                .orElseThrow();
            var cuentasCliente = this.cuentasRepository.findByCliente(cliente)
                .stream().map(cuenta -> {
                    cuenta.setEstado(TipoEstadoEnum.F);
                    return cuenta;
                }).toList();
            this.cuentasRepository.saveAll(cuentasCliente);
            LOGGER.info("Evento de cliente eliminado procesado de menera correcta");
        } catch (Exception exc) {
            LOGGER.info("Se ha producido un error procesando el evento de eliminacion {0}.", exc);
        }
    }
}
