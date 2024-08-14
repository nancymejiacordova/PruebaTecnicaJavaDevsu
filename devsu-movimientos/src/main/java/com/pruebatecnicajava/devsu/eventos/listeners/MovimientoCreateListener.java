/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.eventos.listeners;

import com.pruebatecnicajava.devsu.eventos.MovimientoCreateEvent;
import com.pruebatecnicajava.devsu.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nancy Mejia
 */
@Component
@RequiredArgsConstructor
public class MovimientoCreateListener implements ApplicationListener<MovimientoCreateEvent> {
  private final CuentaRepository cuentaRepository;
    @Override
    public void onApplicationEvent(MovimientoCreateEvent event) {
        var movimiento = event.getMovimiento();
        var cuenta = this.cuentaRepository.findBynumerocuenta(movimiento.getCuenta().getNumerocuenta()).orElseThrow();
        var nuevoSaldo = cuenta.getSaldoinicial().add(movimiento.getValor());
        cuenta.setSaldoinicial(nuevoSaldo);
        this.cuentaRepository.save(cuenta);
    }  
}
