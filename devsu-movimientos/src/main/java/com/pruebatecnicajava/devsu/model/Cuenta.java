/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.model;

import com.pruebatecnicajava.devsu.enums.TipoCuentaEnum;
import com.pruebatecnicajava.devsu.enums.TipoEstadoEnum;
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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
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
@Table(name="cuentas", uniqueConstraints = {
    @UniqueConstraint(columnNames={"numerocuenta"})})
public class Cuenta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idcuenta;
    
    @Column(unique=true,length=10,nullable=false)
    private String numerocuenta;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCuentaEnum tipocuenta;
    
    private BigDecimal saldoinicial;
    
    @Enumerated(EnumType.STRING)
    private TipoEstadoEnum estado;
   
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    
}
