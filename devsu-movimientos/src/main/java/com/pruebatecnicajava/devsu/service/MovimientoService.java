/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.service;

import com.pruebatecnicajava.devsu.templates.MovimientoSaldo;
import com.pruebatecnicajava.devsu.templates.MovimientoTemp;
import com.pruebatecnicajava.devsu.templates.MovimientosRpt;
import jakarta.annotation.Nullable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Nancy Mejia
 */
public interface MovimientoService {
    /**
     * Realiza el registro de un movimiento sobre la cuenta
     * @param movimientoTemp
     * @return {@link MovimientoSaldo}
     */
    MovimientoSaldo registrar(MovimientoTemp movimientoTemp);

    /**
     * Genera el reporte de movimientos para un cliente
     * @param clienteId {@link Long}
     * @param fechaInicial {@link LocalDate}
     * @param fechaFin {@link LocalDate}
     * @return Lista de movimientos {@link List<MovimientoRpt>}
     */
    List<MovimientosRpt> reporte(Long clienteId, LocalDate fechaInicial, @Nullable LocalDate fechaFin);
}
