/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.utilities.convert;

import com.pruebatecnicajava.devsu.model.Cliente;
import org.springframework.core.convert.converter.Converter;
import com.pruebatecnicajava.devsu.utilities.enums.EstadoClienteEnum;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteSecTemp;

/**
 *
 * @author Nancy Mejia
 */

public class ClienteConverTemplate implements Converter<Cliente,ClienteSecTemp> {

    @Override
    public ClienteSecTemp convert(Cliente c) {
    return ClienteSecTemp.builder()
            .clienteId(c.getClienteId())
            .nombre(c.getNombre())
            .genero(c.getGenero().name())
            .edad(c.getEdad())
            .identificacion(c.getIdentificacion())
            .direccion(c.getDireccion())
            .telefono(c.getTelefono())
            .estado(c.getEstado().name()).build();
    }
    
    
    
    
    
}
