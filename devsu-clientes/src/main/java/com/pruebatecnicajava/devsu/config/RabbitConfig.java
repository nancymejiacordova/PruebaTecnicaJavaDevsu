/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.config;

import com.pruebatecnicajava.devsu.utilities.templates.RabbitTemp;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Nancy Mejia
 */
@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
      private final RabbitTemp rabbitMQProp;

    @Bean
    public Queue queue(){
        return new Queue(rabbitMQProp.getName());
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(rabbitMQProp.getExchange());
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
            .to(exchange())
            .with(rabbitMQProp.getRouting().getKey());
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
