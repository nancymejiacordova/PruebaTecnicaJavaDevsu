/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.cliente.config.cliente.data;

import com.pruebatecnicajava.devsu.model.Cliente;
import com.pruebatecnicajava.devsu.utilities.enums.EstadoClienteEnum;
import com.pruebatecnicajava.devsu.utilities.enums.GeneroEnum;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteCreateTemp;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteSecTemp;
import java.util.List;

/**
 *
 * @author Nancy Mejia
 */
public class ClientData {

     
    public static List<Cliente> getListClientEntityEmpty() {
        return List.of();
    }
    
    
    public static List<ClienteSecTemp> getListClienteSecTemp() {
        return List.of(getclienteSecTemp());
    }
    
     public static List<Cliente> getListClientModel() {
        return List.of(Cliente.builder()
                .clienteId(123L)
                .nombre("Nombre Cliente")
                .estado(EstadoClienteEnum.TRUE)
                .direccion("Direccion")
                .edad(39)
                .genero(GeneroEnum.MASCULINO)
                .password("123456")
            .build());
    }


    public static Cliente getClient() {
        return Cliente.builder()
            .clienteId(123L)
            .nombre("Nombre Cliente")
            .estado(EstadoClienteEnum.TRUE)
            .direccion("Direccion")
            .edad(39)
            .genero(GeneroEnum.FEMENINO)
            .password("123456")
            .identificacion("E-14596523")
            .telefono("74859632")
            .build();
    }

    public static ClienteCreateTemp getClienteCreateTemp() {
        return ClienteCreateTemp.builder()
            .nombre("Nombre Cliente")
            .genero(GeneroEnum.OTRO)
            .edad(39)
            .identificacion("E-14596523")
            .direccion("Direccion")
            .telefono("74859632")
            .password("123456")
            .build();
    }


    public static ClienteSecTemp getclienteSecTemp() {
            return ClienteSecTemp.builder()
            .clienteId(123L)
            .nombre("Nombre Cliente")
            .genero(GeneroEnum.MASCULINO.name())
            .edad(39L)
            .identificacion("E-14596523")
            .direccion("Direccion")
            .telefono("74859632")
            .estado(EstadoClienteEnum.getEstadoString(EstadoClienteEnum.TRUE))
            .build();
    }
    
}
