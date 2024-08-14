/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.eventos.publisher;

import com.pruebatecnicajava.devsu.eventos.MovimientoCreateEvent;
import com.pruebatecnicajava.devsu.model.Movimiento;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nancy Mejia
 */
@Component
@RequiredArgsConstructor
public class MovimientoCreatePublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoCreatePublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Movimiento movimiento) {
        LOGGER.info("Publicando evento de movimiento creado.");
        MovimientoCreateEvent movimientoCreadoEvent = new MovimientoCreateEvent(this, movimiento);
        applicationEventPublisher.publishEvent(movimientoCreadoEvent);
    }
}
