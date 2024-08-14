/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.utilities.eventos.cliente;

import com.pruebatecnicajava.devsu.utilities.eventos.EventoGenerico;
import lombok.Getter;

/**
 *
 * @author Nancy Mejia
 */
@Getter
public class ClienteModEv extends EventoGenerico{
    
    private Long clienteId;
    private String nombre;

    public ClienteModEv(Long clienteId, String nombre) {
        super("CLIENTE_MODIFICADO");
        this.clienteId = clienteId;
        this.nombre = nombre;
    }
}
