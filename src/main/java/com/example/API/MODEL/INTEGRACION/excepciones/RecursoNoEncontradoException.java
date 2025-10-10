package com.example.API.MODEL.INTEGRACION.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para manejar errores cuando un recurso no es encontrado.
 * Retorna un estado HTTP 404 con un mensaje descriptivo.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

