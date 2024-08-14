/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.service.pub;

import com.pruebatecnicajava.devsu.utilities.eventos.EventoGenerico;
import com.pruebatecnicajava.devsu.utilities.templates.RabbitTemp;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nancy Mejia
 */

@Service
@RequiredArgsConstructor
public class ClientePubEv {
      private static final Logger LOGGER = LoggerFactory.getLogger(ClientePubEv.class);
    private final RabbitTemp rabbitMQProp;
    private final RabbitTemplate rabbitTemplate;

    public void publicarEvento(EventoGenerico event) {
        try {
            LOGGER.info("Publicando evento -> {}",event);
            this.rabbitTemplate.convertAndSend(rabbitMQProp.getExchange(), rabbitMQProp.getRouting().getKey(), event);
        } catch (AmqpException amqpException) {
            LOGGER.error("Se ha presentado un error publicando el evento {}, causado por {}", event, amqpException.getMessage());
        }
    }
}
