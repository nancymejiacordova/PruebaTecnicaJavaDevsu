/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pruebatecnicajava.devsu.service;

import com.pruebatecnicajava.devsu.utilities.templates.ClienteCreateTemp;
import com.pruebatecnicajava.devsu.utilities.templates.ClienteSecTemp;
import java.util.List;

/**
 *
 * @author Nancy Mejia
 */
public interface ClienteService {
    
    List<ClienteSecTemp> listClientes();
    ClienteSecTemp buscarCliente(Long idCliente);
    ClienteSecTemp crearCliente(ClienteCreateTemp cliente);
    ClienteSecTemp actualizarCliente(Long idCliente,ClienteCreateTemp cliente);
    void elimCliente(Long idCliente);
}
