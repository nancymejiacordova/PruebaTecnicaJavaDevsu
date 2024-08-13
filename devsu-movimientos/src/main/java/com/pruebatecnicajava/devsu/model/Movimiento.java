/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.model;

import com.pruebatecnicajava.devsu.enums.TipoCuentaEnum;
import com.pruebatecnicajava.devsu.enums.TipoMovimientoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Nancy Mejia
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmovimiento;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimientoEnum tipomovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idcuenta")
    private Cuenta cuenta;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now(ZoneId.systemDefault());
    }

}
