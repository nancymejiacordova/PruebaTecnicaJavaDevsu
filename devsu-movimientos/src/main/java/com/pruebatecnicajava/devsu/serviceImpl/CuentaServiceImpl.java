/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.serviceImpl;

import com.pruebatecnicajava.devsu.enums.TipoEstadoEnum;
import com.pruebatecnicajava.devsu.exceptions.ErrorEnum;
import com.pruebatecnicajava.devsu.exceptions.Exceptions;
import com.pruebatecnicajava.devsu.model.Cuenta;
import com.pruebatecnicajava.devsu.repository.CuentaRepository;
import com.pruebatecnicajava.devsu.service.ClienteService;
import com.pruebatecnicajava.devsu.service.CuentaService;
import com.pruebatecnicajava.devsu.templates.CuentaCreateTemp;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nancy Mejia
 */
@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService{
    
    private final CuentaRepository cuentaRepository;
    private final ConversionService conversionService;
    private final ClienteService clienteService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Cuenta> listCuentas(Pageable pageable) {
        return this.cuentaRepository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Cuenta buscarCuenta(String numerocuenta) {
        Cuenta cuentaEntity = this.findBynumerocuenta(numerocuenta);
        return cuentaEntity;
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Cuenta actualizarMontoCuenta(Cuenta cuenta, BigDecimal monto) {
        cuenta.setSaldoinicial(monto);
        Cuenta cuentaEntity = this.cuentaRepository.save(cuenta);
        return cuentaEntity;
    }
    @Override
    @Transactional()
    public Cuenta crearCuenta(CuentaCreateTemp cuentaCreateTemp) {

        if (cuentaCreateTemp.getSaldoInicial().compareTo(BigDecimal.ZERO) < 0) {
            throw new Exceptions(ErrorEnum.CREAR_CUENTA_SALDO_INVALIDO, HttpStatus.BAD_REQUEST.value());
        }

        var cliente = clienteService.buscarCliente(cuentaCreateTemp.getCliente().getClienteId());
        var cuentaEntity = Cuenta.builder()
            .cliente(cliente)
            .numerocuenta(cuentaCreateTemp.getNumerocuenta())
            .tipocuenta(cuentaCreateTemp.getTipocuenta())
            .saldoinicial(cuentaCreateTemp.getSaldoInicial())
            .estado(cuentaCreateTemp.getEstado())
            .build();
        return this.cuentaRepository.save(cuentaEntity);
       // return this.conversionService.convert(cuentaEntity, CuentaSecTemp.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional()
    public void eliminarCuenta(String numeroCuenta) {
        var cuentaEntity = this.findBynumerocuenta(numeroCuenta);
        cuentaEntity.setEstado(TipoEstadoEnum.F);
        this.cuentaRepository.save(cuentaEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta findBynumerocuenta(String numerocuenta) {
        return this.cuentaRepository.findBynumerocuenta(numerocuenta)
            .orElseThrow(() ->new Exceptions(ErrorEnum.CUENTA_NOT_FOUND, HttpStatus.NOT_FOUND.value()));
    }

}
