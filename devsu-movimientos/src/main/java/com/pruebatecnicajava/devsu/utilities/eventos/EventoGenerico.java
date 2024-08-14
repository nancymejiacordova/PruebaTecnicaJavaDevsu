/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.utilities.eventos;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;

/**
 *
 * @author Nancy Mejia
 */
@Getter
public abstract class EventoGenerico {
    
    private final String idEvento;
    private final String accion;
    private final Date fechaEvento;
    
     protected EventoGenerico(String accion) {
        this.idEvento = UUID.randomUUID().toString();
        this.fechaEvento = Date.from(Instant.now());
        this.accion = accion;
    }


}
