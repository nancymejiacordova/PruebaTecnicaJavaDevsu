/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.cliente.publish;

import com.pruebatecnicajava.devsu.service.pub.ClientePubEv;
import com.pruebatecnicajava.devsu.utilities.eventos.ClienteEv.ClienteCreadoEv;
import com.pruebatecnicajava.devsu.utilities.eventos.ClienteEv.ClienteElimEv;
import com.pruebatecnicajava.devsu.utilities.eventos.ClienteEv.ClienteModEv;
import com.pruebatecnicajava.devsu.utilities.eventos.EventoGenerico;
import com.pruebatecnicajava.devsu.utilities.templates.RabbitTemp;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

/**
 *
 * @author Nancy Mejia
 */
@ExtendWith(OutputCaptureExtension.class)
class ClientEventProducerTest {
    @Mock
    private RabbitTemp rabbitTemp;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private RabbitTemp.Routing routing;
    @InjectMocks
    private ClientePubEv clientPubEv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(rabbitTemp.getExchange()).thenReturn("text_exchange");
        Mockito.when(rabbitTemp.getName()).thenReturn("text_quote");
        Mockito.when(rabbitTemp.getRouting()).thenReturn(routing);
        Mockito.when(routing.getKey()).thenReturn("test_routingKey");
    }

    @ParameterizedTest
    @MethodSource("provideClientEvent")
    void publicarEvento(EventoGenerico event) {
        Mockito.doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString(), Mockito.any(Object.class));
        this.clientPubEv.publicarEvento(event);
        Mockito.verify(rabbitTemplate, times(1))
            .convertAndSend(anyString(), anyString(), any(Object.class));
    }

    @Test
    void publicarEventoException(CapturedOutput output){
        var evento = new ClienteModEv(123L,"Cliente Prueba");
        doThrow(new AmqpException("Error al publicar evento"))
            .when(rabbitTemplate).convertAndSend(anyString(), anyString(), Mockito.any(Object.class));
        this.clientPubEv.publicarEvento(evento);
        Assertions.assertTrue(output.getOut().contains("Se ha presentado un error publicando el evento"));
    }

    private static Stream<Arguments> provideClientEvent() {
        return Stream.of(
            Arguments.of(new ClienteCreadoEv(123L,"Cliente Prueba")),
            Arguments.of(new ClienteModEv(123L,"Cliente Prueba")),
            Arguments.of(new ClienteElimEv(123L, "Cliente Prueba"))
        );
    }
}
