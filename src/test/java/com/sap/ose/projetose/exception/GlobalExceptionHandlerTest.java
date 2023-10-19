package com.sap.ose.projetose.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    @Test
    void testHandleDataIntegrityViolation() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> actualHandleDataIntegrityViolationResult = globalExceptionHandler
                .handleDataIntegrityViolation(new DataIntegrityViolationException("Msg"));
        assertEquals("Msg", actualHandleDataIntegrityViolationResult.getBody());
        assertEquals(400, actualHandleDataIntegrityViolationResult.getStatusCodeValue());
        assertTrue(actualHandleDataIntegrityViolationResult.getHeaders().isEmpty());
    }

    @Test
    void testHandleDataAccessException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> actualHandleDataAccessExceptionResult = globalExceptionHandler
                .handleDataAccessException(new EmptyResultDataAccessException(3));
        assertEquals("Incorrect result size: expected 3, actual 0", actualHandleDataAccessExceptionResult.getBody());
        assertEquals(500, actualHandleDataAccessExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleDataAccessExceptionResult.getHeaders().isEmpty());
    }


    @Test
    void testHandleUnknownException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> actualHandleUnknownExceptionResult = globalExceptionHandler
                .handleUnknownException(new Exception("foo"));
        assertEquals("foo", actualHandleUnknownExceptionResult.getBody());
        assertEquals(500, actualHandleUnknownExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleUnknownExceptionResult.getHeaders().isEmpty());
    }

}

