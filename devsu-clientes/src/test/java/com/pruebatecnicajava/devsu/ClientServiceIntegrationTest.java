/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Nancy Mejia
 */

@ExtendWith(MockitoExtension.class)
public class ClientServiceIntegrationTest {
    
    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:karate").relativeTo(getClass());
    }
   
    private static final WireMockServer wireMockServer
            = new WireMockServer(WireMockConfiguration.options().port(8095));

    @BeforeAll
    public static void setUp() {
        wireMockServer.start();
        configureFor("localhost", 8095);
        stubFor(
                get(urlEqualTo("/clientes/1"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{ \"id\": \"1234\", \"name\": \"Nancy\", \"age\": 35," +
                                        "\"phone\": \"123456789\", \"address\": \"Calle 9\"} "
                                )));
        stubFor(
                get(urlEqualTo("/clientes"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{ \"id\": \"1234\", \"name\": \"Nancy\", \"age\": 35," +
                                        "\"phone\": \"123456789\", \"address\": \"Calle 9\"} "
                                )));
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

}