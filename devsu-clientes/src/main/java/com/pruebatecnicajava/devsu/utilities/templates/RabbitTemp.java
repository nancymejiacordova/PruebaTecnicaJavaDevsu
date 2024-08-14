/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.utilities.templates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nancy Mejia
 */
@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "rabbitmq.queue")
public class RabbitTemp {
    
    private String name;
    private String exchange;
    private Routing routing;
    @Data
    public static class Routing {
        private String key;
    }
}
