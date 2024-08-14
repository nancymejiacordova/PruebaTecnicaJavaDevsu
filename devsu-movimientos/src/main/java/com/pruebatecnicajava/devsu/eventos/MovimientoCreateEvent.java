/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.eventos;

import com.pruebatecnicajava.devsu.model.Movimiento;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author Nancy Mejia
 */
@Getter
public class MovimientoCreateEvent extends ApplicationEvent{
    
    private final Movimiento movimiento;
    public MovimientoCreateEvent(Object source, Movimiento movimiento) {
        super(source);
        this.movimiento = movimiento;
    }
}
