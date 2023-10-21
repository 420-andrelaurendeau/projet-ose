package com.sap.ose.projetose.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalExceptionHandlerTest {

    @Test
    void testHandleDataIntegrityViolation() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> actualHandleDataIntegrityViolationResult = globalExceptionHandler
                .handleDataIntegrityViolation(new DataIntegrityViolationException("Msg"));
        assertEquals("Msg", actualHandleDataIntegrityViolationResult.getBody());
        assertEquals(400, actualHandleDataIntegrityViolationResult.getStatusCode().value());
        assertTrue(actualHandleDataIntegrityViolationResult.getHeaders().isEmpty());
    }

    @Test
    void testHandleDataAccessException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> actualHandleDataAccessExceptionResult = globalExceptionHandler
                .handleDataAccessException(new EmptyResultDataAccessException(3));
        assertEquals("Incorrect result size: expected 3, actual 0", actualHandleDataAccessExceptionResult.getBody());
        assertEquals(500, actualHandleDataAccessExceptionResult.getStatusCode().value());
        assertTrue(actualHandleDataAccessExceptionResult.getHeaders().isEmpty());
    }


    @Test
    void testHandleUnknownException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> actualHandleUnknownExceptionResult = globalExceptionHandler
                .handleUnknownException(new Exception("foo"));
        assertEquals("foo", actualHandleUnknownExceptionResult.getBody());
        assertEquals(500, actualHandleUnknownExceptionResult.getStatusCode().value());
        assertTrue(actualHandleUnknownExceptionResult.getHeaders().isEmpty());
    }

}

