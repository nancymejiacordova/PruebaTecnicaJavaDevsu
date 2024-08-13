/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.service;

import com.pruebatecnicajava.devsu.model.Cuenta;
import com.pruebatecnicajava.devsu.templates.CuentaCreateTemp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Nancy Mejia
 */
public interface CuentaService {
    /**
     * Obtiene un listado de cuentas paginadas
     * @param pageable
     * @return {@link Page<CuentaSecTemp>}
     */
    Page<Cuenta> listCuentas(Pageable pageable);

    /**
     * Obtiene detalle de una cuenta indicada
     * @param numerocuenta
     * @return {@link CuentaSecTemp}
     */
    Cuenta buscarCuenta(String numerocuenta);
    
     //Cuenta buscarCuentaa(String numeroCuenta);

    /**
     *
     * @param numeroCuenta
     * @return
     */
    //Cuenta buscarCuentaa(String numeroCuenta);

    /**
     * Crea una cuenta nueva en la base de datos
     * @param cuentaCreateTemp
     * @return  {@link CuentaSecTemp}
     */
    Cuenta crearCuenta(CuentaCreateTemp cuentaCreateTemp);

    /**
     * Elimina una cuenta de manera logica
     * @param numerocuenta
     */
    void eliminarCuenta(String numerocuenta);

    Cuenta findBynumerocuenta(String numerocuenta);

    
}
