/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnicajava.devsu.config;

import com.pruebatecnicajava.devsu.exceptions.ErrorEnum;
import com.pruebatecnicajava.devsu.exceptions.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Nancy Mejia
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return this.handleExceptionInternal(ex, new Exceptions(ErrorEnum.INVALID_ARGS), headers, status, request);
    }
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        LOGGER.warn("Metodo http no soportado: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, new Exceptions(ErrorEnum.NOT_ALLOWED), headers, status, request);
    }

    protected ResponseEntity<Object> handleServletRequestBindingException(final ServletRequestBindingException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return this.handleExceptionInternal(ex, new Exceptions(ErrorEnum.NOT_ALLOWED), headers, status, request);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return this.handleExceptionInternal(ex, new Exceptions(ErrorEnum.INVALID_BODY), headers, status, request);
    }

    @ExceptionHandler({Exceptions.class})
    public ResponseEntity<Object> handleExceptions(final Exceptions ex, final WebRequest webRequest) {
        LOGGER.warn("Hubo una excepcion controlada. Estado. {}, Codigo: {}, Mensaje: {} ", ex.getStatus(), ex.getCode(), ex.getMessage(), ex);
        return this.doHandleExceptions(ex, webRequest);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGeneralException(final Exception ex, final WebRequest webRequest) {
        LOGGER.error("Excepcion NO manejada de la aplicacion.", ex);
        return this.handleExceptionInternal(ex, new Exceptions(ErrorEnum.DEFAULT), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    private ResponseEntity<Object> doHandleExceptions(final Exceptions ex, final WebRequest webRequest) {
        return this.handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.valueOf(ex.getStatus()), webRequest);
    }
    
}
