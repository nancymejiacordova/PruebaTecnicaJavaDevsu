/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.repository.integracion;

import com.pruebatecnicajava.devsu.utilities.eventos.EventoGenerico;

/**
 *
 * @author Nancy Mejia
 */
public interface ClienteIntegracionRepository {
        void procesarEvento(EventoGenerico evento);

}
