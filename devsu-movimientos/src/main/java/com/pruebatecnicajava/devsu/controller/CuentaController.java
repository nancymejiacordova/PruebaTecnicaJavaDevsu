/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.controller;

import com.pruebatecnicajava.devsu.exceptions.ErrorEnum;
import com.pruebatecnicajava.devsu.exceptions.Exceptions;
import com.pruebatecnicajava.devsu.model.Cuenta;
import com.pruebatecnicajava.devsu.service.CuentaService;
import com.pruebatecnicajava.devsu.templates.CuentaCreateTemp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nancy Mejia
 */
@RestController
@RequestMapping(path = "/cuentas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CuentaController {
     private final CuentaService cuentaService;


    @GetMapping()
    public Page<Cuenta> listar(Pageable pageable){
        var listCuentas = this.cuentaService.listCuentas(pageable);
        if (!listCuentas.hasContent()) {
            throw new Exceptions(ErrorEnum.NO_CONTENT, HttpStatus.NO_CONTENT.value());
        }
        return listCuentas;
    }

    
    @GetMapping(value = "/detalle/{numeroCuenta}")
    public Cuenta buscarPorId(@PathVariable String numeroCuenta) {
        return this.cuentaService.buscarCuenta(numeroCuenta);
    }

    

    /**
     *
     * @param cuentaCreateTemp
     * @return
     */

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cuenta crear(@RequestBody CuentaCreateTemp cuentaCreateTemp) {
        return this.cuentaService.crearCuenta(cuentaCreateTemp);
    }

    
    @DeleteMapping(value = "/{numeroCuenta}")
    public void eliminar(@PathVariable String numeroCuenta) {
        this.cuentaService.eliminarCuenta(numeroCuenta);
    }

}
