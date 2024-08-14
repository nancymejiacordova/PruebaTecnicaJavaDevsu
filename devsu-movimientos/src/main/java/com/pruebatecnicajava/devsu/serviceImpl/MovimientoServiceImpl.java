/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.serviceImpl;

import com.pruebatecnicajava.devsu.exceptions.ErrorEnum;
import com.pruebatecnicajava.devsu.exceptions.Exceptions;
import com.pruebatecnicajava.devsu.model.Cuenta;
import com.pruebatecnicajava.devsu.model.Movimiento;
import com.pruebatecnicajava.devsu.repository.CuentaRepository;
import com.pruebatecnicajava.devsu.repository.MovimientoRepository;
import com.pruebatecnicajava.devsu.service.CuentaService;
import com.pruebatecnicajava.devsu.service.MovimientoService;
import com.pruebatecnicajava.devsu.templates.MovimientoCreateTemp;
import com.pruebatecnicajava.devsu.templates.MovimientoSaldo;
import com.pruebatecnicajava.devsu.templates.MovimientoTemp;
import com.pruebatecnicajava.devsu.templates.MovimientosRpt;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nancy Mejia
 */
@Service
@RequiredArgsConstructor

public class MovimientoServiceImpl implements MovimientoService {
    
    private final MovimientoRepository movimientoRepository;
    private final CuentaService cuentaService;
    private final CuentaRepository cuentaReposotory;
   // private final MovimientoCreadoPublisher movimientoCreadoPublisher;

    /**
     *
     * @param movimientoTemp
     * @return
     */
    @Override
    @Transactional
    public MovimientoSaldo registrar(MovimientoTemp movimientoTemp) {

        var cuenta = this.cuentaService.findBynumerocuenta(movimientoTemp.getNumerocuenta());
        var valorMovimiento = movimientoTemp.getValor();
        if (cuenta.getSaldoinicial().add(valorMovimiento).compareTo(BigDecimal.ZERO) < 0) {
            throw new Exceptions(ErrorEnum.SALDO_INSUFICIENTE, HttpStatus.BAD_REQUEST.value());
        }
        var movimiento = Movimiento.builder()
            .cuenta(cuenta)
            .valor(valorMovimiento)
            .tipomovimiento(movimientoTemp.getTipoMovimiento())
            .saldo(cuenta.getSaldoinicial().add(valorMovimiento))
            .build();
        this.cuentaService.actualizarMontoCuenta(cuenta, movimiento.getSaldo());
        this.movimientoRepository.save(movimiento);
        return MovimientoSaldo.builder()
            .cuenta(cuenta.getNumerocuenta())
            .idMovimiento(String.valueOf(movimiento.getIdmovimiento()))
            .saldoDisponible(movimiento.getSaldo())
            .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientosRpt> reporte(Long clienteId, LocalDate fechaInicial, LocalDate fechaFin) {

        var fechaDia = LocalDate.now(ZoneId.systemDefault());
        var fechaConsultaFin =  Objects.requireNonNullElse(fechaFin, fechaDia);
        if (fechaInicial.isAfter(fechaDia) || fechaConsultaFin.isAfter(fechaDia)) {
            throw new Exceptions(ErrorEnum.ERROR_FECHA_INVALIDA, HttpStatus.BAD_REQUEST.value());
        }
        if (fechaConsultaFin.isBefore(fechaInicial)) {
            throw new Exceptions(ErrorEnum.ERROR_FECHA_FIN_INVALIDA, HttpStatus.BAD_REQUEST.value());
        }

        var fechaStart = fechaInicial.atStartOfDay();
        var fechaEnd = fechaFin.atStartOfDay();
         
        var movimientos = this.movimientoRepository.findAllByClienteAndFecha(clienteId, fechaStart, fechaEnd, Sort.by("fecha").descending());
        return movimientos.stream().map(movimiento -> MovimientosRpt.builder()
            .fecha(movimiento.getFecha().toLocalDate())
            .cliente(movimiento.getCuenta().getCliente().getNombre())
            .numeroCuenta(movimiento.getCuenta().getNumerocuenta())
            .tipoCuenta(movimiento.getCuenta().getTipocuenta().toString())
            .saldoInicial(movimiento.getSaldo())
            .estado(movimiento.getCuenta().getEstado().valueBoolean())
            .movimiento(movimiento.getValor())
            .saldoDisponible(movimiento.getSaldo())
            .build()).toList();
    }

   
    
}
