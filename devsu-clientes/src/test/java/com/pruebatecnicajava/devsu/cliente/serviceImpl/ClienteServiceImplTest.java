/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.cliente.serviceImpl;

import com.pruebatecnicajava.devsu.cliente.config.cliente.data.ClientData;
import com.pruebatecnicajava.devsu.exceptions.ErrorEnum;
import com.pruebatecnicajava.devsu.exceptions.Exceptions;
import com.pruebatecnicajava.devsu.model.Cliente;
import com.pruebatecnicajava.devsu.repository.ClienteRepository;
import com.pruebatecnicajava.devsu.service.Impl.ClienteServiceImpl;
import com.pruebatecnicajava.devsu.service.pub.ClientePubEv;
import com.pruebatecnicajava.devsu.utilities.enums.EstadoClienteEnum;
import com.pruebatecnicajava.devsu.utilities.enums.GeneroEnum;
import com.pruebatecnicajava.devsu.utilities.eventos.ClienteEv.ClienteCreadoEv;
import com.pruebatecnicajava.devsu.utilities.eventos.ClienteEv.ClienteElimEv;
import com.pruebatecnicajava.devsu.utilities.eventos.ClienteEv.ClienteModEv;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteCreateTemp;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteSecTemp;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Nancy Mejia
 */
public class ClienteServiceImplTest {
     @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ConversionService conversionService;
    @Mock
    private ClientePubEv clientePubEv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listClientEmpty() {
        var clienteModel = Cliente.builder().build();
        when(clienteRepository.findAll(any(Sort.class)))
            .thenReturn(ClientData.getListClientEntityEmpty());
        var listado = clienteServiceImpl.listClientes();
        assertNotNull(listado);
        assertTrue(listado.isEmpty());
        verify(conversionService,never()).convert(clienteModel, ClienteSecTemp.class);
    }

    @Test
    void listClient() {
        var clienteEntity = Cliente.builder().build();
        var clienteVm = ClienteSecTemp.builder().build();
        var lstaClientes = ClientData.getListClientModel();
        when(clienteRepository.findAll())
            .thenReturn(lstaClientes);
        when(conversionService.convert(clienteEntity, ClienteSecTemp.class)).thenReturn(clienteVm);
        var listado = clienteServiceImpl.listClientes();
        assertNotNull(listado);
        assertFalse(listado.isEmpty());
    }

    @Test
    void searchClientExist() {
        var expectResponse = ClientData.getClient();
        var clienteVm = ClienteSecTemp.builder().build();
        when(clienteRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(expectResponse));
        when(conversionService.convert(expectResponse, ClienteSecTemp.class)).thenReturn(clienteVm);
        var resultado = clienteServiceImpl.buscarCliente(123L);
        assertNotNull(resultado);
        assertInstanceOf(ClienteSecTemp.class, resultado);
    }

    @Test
    void searchClientNoExist() {
        when(clienteRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());
        var throwable = assertThrows(Exceptions.class, () -> clienteServiceImpl.buscarCliente(98745L));
        Assertions.assertEquals(ErrorEnum.CLIENTE_NOT_FOUND, throwable.getErrorEnum());
    }

    @Test
    void createClient() {
        var clientCreate = ClientData.getClienteCreateTemp();
        var clienteVm = ClienteSecTemp.builder().build();
        var clienteEntity = Cliente.builder()
            .clienteId(123L)
            .nombre(clientCreate.getNombre())
            .genero(clientCreate.getGenero())
            .edad(clientCreate.getEdad())
            .identificacion(clientCreate.getIdentificacion())
            .direccion(clientCreate.getDireccion())
            .telefono(clientCreate.getTelefono())
            .password(clientCreate.getPassword())
            .estado(EstadoClienteEnum.TRUE)
            .build();

        when(clienteRepository.save(ArgumentMatchers.any(Cliente.class))).thenReturn(clienteEntity);
        when(conversionService.convert(clienteEntity, ClienteSecTemp.class)).thenReturn(clienteVm);
        doNothing().when(clientePubEv).publicarEvento(any(ClienteCreadoEv.class));
        clienteServiceImpl.crearCliente(clientCreate);
        verify(clienteRepository,times(1)).save(any(Cliente.class));
        verify(clientePubEv,times(1)).publicarEvento(any(ClienteCreadoEv.class));
    }
    @Test
    void updateClientNotExist() {
        var clienteRequest = ClienteCreateTemp.builder().build();
        when(clienteRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());
        var throwable = assertThrows(Exceptions.class,
            () -> clienteServiceImpl.actualizarCliente(98745L, clienteRequest));
        Assertions.assertEquals(ErrorEnum.CLIENTE_NOT_FOUND, throwable.getErrorEnum());
        verify(clientePubEv,never()).publicarEvento(any(ClienteModEv.class));
    }

    @Test
    void deleteClient() {
        var expectResponse = ClientData.getClient();
        when(clienteRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(expectResponse));
        doNothing().when(clientePubEv).publicarEvento(any(ClienteModEv.class));
        this.clienteServiceImpl.elimCliente(123L);
        expectResponse.setEstado(EstadoClienteEnum.FALSE);
        verify(this.clienteRepository, times(1)).save(expectResponse);
        verify(clientePubEv,times(1)).publicarEvento(any(ClienteElimEv.class));
    }

    @Test
    void deleteClientNoExist() {
        when(clienteRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());
        var throwable = assertThrows(Exceptions.class, () -> clienteServiceImpl.elimCliente(98745L));
        Assertions.assertEquals(ErrorEnum.CLIENTE_NOT_FOUND, throwable.getErrorEnum());
        verify(clientePubEv,never()).publicarEvento(any(ClienteElimEv.class));
    }

    private static Stream<Arguments> provideClientCreateTemp() {
        return Stream.of(
            Arguments.of(ClienteCreateTemp.builder().build()),
            Arguments.of(ClienteCreateTemp.builder()
                    .nombre("Nombre Actualizado")
                    .genero(GeneroEnum.MASCULINO)
                    .edad(40)
                    .identificacion("V-15748965")
                    .direccion("Direccion Actualizada")
                    .telefono("228254785")
                    .password("12345678")
                .build())
        );
    }
    
}
