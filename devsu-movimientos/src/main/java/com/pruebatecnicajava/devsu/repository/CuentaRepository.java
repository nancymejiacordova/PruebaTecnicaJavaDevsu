/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.repository;

import com.pruebatecnicajava.devsu.model.Cliente;
import com.pruebatecnicajava.devsu.model.Cuenta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nancy Mejia
 */
@Repository
public interface CuentaRepository  extends JpaRepository<Cuenta, Long>{
    
    Optional<Cuenta> findBynumerocuenta(String numerocuenta);

     List<Cuenta> findByCliente(Cliente cliente);
    
}
