/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.repository;

import com.pruebatecnicajava.devsu.model.Movimiento;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nancy Mejia
 */
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
 
    List<Movimiento> findAllByFechaBetween(LocalDateTime fechaStart, LocalDateTime fechaEnd);

    @Query("SELECT m, c FROM Movimiento m LEFT JOIN m.cuenta c  " +
        " LEFT JOIN c.cliente c1 WHERE c1.clienteId = ?1 AND m.fecha between ?2 AND ?3")
    List<Movimiento> findAllByClienteAndFecha(Long clienteId, LocalDateTime fechaStart, LocalDateTime fechaEnd, Sort sort);

}
