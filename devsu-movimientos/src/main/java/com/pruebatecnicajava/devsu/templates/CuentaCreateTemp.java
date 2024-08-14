/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.templates;

import com.pruebatecnicajava.devsu.enums.TipoCuentaEnum;
import com.pruebatecnicajava.devsu.enums.TipoEstadoEnum;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Nancy Mejia
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaCreateTemp {
    private String numerocuenta;
    private TipoCuentaEnum tipocuenta;
    private BigDecimal saldoInicial;
    private TipoEstadoEnum estado;
    private ClienteTemp cliente;

}
