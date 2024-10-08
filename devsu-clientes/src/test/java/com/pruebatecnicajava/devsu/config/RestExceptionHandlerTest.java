/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.WebRequest;


/**
 *
 * @author Nancy Mejia
 */

class RestExceptionHandlerTest {
    
    @InjectMocks
    private RestExceptionHandler restExceptionHandler;
    @Mock
    private WebRequest webRequest;
    private final HttpHeaders httpHeaders = HttpHeaders.EMPTY;
    
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void handleMethodArgumentNotValid() {
        var exception = Mockito.mock(MethodArgumentNotValidException.class);
        var response = restExceptionHandler.handleMethodArgumentNotValid(exception, httpHeaders,
            HttpStatus.BAD_REQUEST, webRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void handleHttpRequestMethodNotSupported() {
        var exception = Mockito.mock(HttpRequestMethodNotSupportedException.class);
        var response = restExceptionHandler.handleHttpRequestMethodNotSupported(exception, httpHeaders,
            HttpStatus.METHOD_NOT_ALLOWED, webRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    void handleServletRequestBindingException() {
        var exception = Mockito.mock(ServletRequestBindingException.class);
        var response = restExceptionHandler.handleServletRequestBindingException(exception, httpHeaders,
            HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    void handleHttpMessageNotReadable() {
        var exception = Mockito.mock(HttpMessageNotReadableException.class);
        var response = restExceptionHandler.handleHttpMessageNotReadable(exception, httpHeaders,
            HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void handleGeneralException() {
        var exception = Mockito.mock(HttpMessageNotReadableException.class);
        var response = restExceptionHandler.handleGeneralException(exception, webRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }    
}
