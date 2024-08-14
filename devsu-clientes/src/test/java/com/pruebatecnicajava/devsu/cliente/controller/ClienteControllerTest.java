/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.cliente.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnicajava.devsu.cliente.config.cliente.data.ClientData;
import com.pruebatecnicajava.devsu.controller.ClienteController;
import com.pruebatecnicajava.devsu.service.ClienteService;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteCreateTemp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Nancy Mejia
 */
@WebMvcTest(controllers = {ClienteController.class})
@ContextConfiguration(classes = ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listar() throws Exception {
        Mockito.when(clienteService.listClientes())
            .thenReturn(ClientData.getListClienteSecTemp());
        mockMvc.perform(get("/clientes"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void buscarPorId() throws Exception {
        Mockito.when(clienteService.buscarCliente(any(Long.class)))
            .thenReturn(ClientData.getclienteSecTemp());
        mockMvc.perform(get("/clientes/ver/{clienteId}", 123))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void crear() throws Exception {
        var request = ClientData.getClienteCreateTemp();
        Mockito.when(clienteService.crearCliente(any(ClienteCreateTemp.class)))
            .thenReturn(ClientData.getclienteSecTemp());
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void actualizar() throws Exception {
        var request = ClientData.getClienteCreateTemp();
        Mockito.when(clienteService.actualizarCliente(any(Long.class), any(ClienteCreateTemp.class)))
            .thenReturn(ClientData.getclienteSecTemp());
        mockMvc.perform(patch("/clientes/{clienteId}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void eliminar() throws Exception {
        Mockito.doNothing().when(clienteService).elimCliente(any(Long.class));
        mockMvc.perform(delete("/clientes/{clienteId}", 123))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
