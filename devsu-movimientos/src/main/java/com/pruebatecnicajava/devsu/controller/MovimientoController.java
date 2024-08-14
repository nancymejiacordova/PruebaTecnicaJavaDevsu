/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.controller;

import com.pruebatecnicajava.devsu.service.MovimientoService;
import com.pruebatecnicajava.devsu.templates.MovimientoSaldo;
import com.pruebatecnicajava.devsu.templates.MovimientoTemp;
import com.pruebatecnicajava.devsu.templates.MovimientosRpt;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nancy Mejia
 */
@RestController
@RequestMapping(path = "/movimientos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MovimientoController {
    
    private final MovimientoService movimientoService;

 
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MovimientoSaldo registrarMovimiento(@RequestBody MovimientoTemp movimientotemp) {
        return this.movimientoService.registrar(movimientotemp);
    }

    @GetMapping(value="/reportes/{clienteId}")
    public List<MovimientosRpt> reporteCliente(@PathVariable Long clienteId,
                                       @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaInicial,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFin)
    {
        return this.movimientoService.reporte(clienteId, fechaInicial, fechaFin);
    }

    
}
