package com.sap.ose.projetose.exception;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.logging.Level;


@ControllerAdvice
@Log
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        var lastUserCodeExecuted = Arrays.stream(e.getStackTrace()).filter(stackTraceElement -> stackTraceElement.getClassName().contains("com.sap.ose.projetose")).findFirst().orElseThrow();
        log.log(Level.WARNING, lastUserCodeExecuted.getClassName() + "." + lastUserCodeExecuted.getMethodName(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OfferAlreadyReviewException.class)
    public ResponseEntity<String> handleOfferAlreadyApprovedException(OfferAlreadyReviewException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProgramNotFoundException.class)
    public ResponseEntity<String> handleProgramNotFoundException(ProgramNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployerNotFoundException.class)
    public ResponseEntity<String> handleEmployerNotFoundException(EmployerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EtudiantNotFoundException.class)
    public ResponseEntity<String> handleEtudiantNotFoundException(EtudiantNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternshipmanagerNotFoundException.class)
    public ResponseEntity<String> handleInternshipCandidateNotFoundException(InternshipmanagerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<String> handleOfferNotFoundException(OfferNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<String> handleDatabaseException(DatabaseException e) {
        var lastUserCodeExecuted = Arrays.stream(e.getStackTrace()).filter(stackTraceElement -> stackTraceElement.getClassName().contains("com.sap.ose.projetose")).findFirst().orElseThrow();
        log.log(Level.WARNING, lastUserCodeExecuted.getClassName() + "." + lastUserCodeExecuted.getMethodName(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadSortingFieldException.class)
    public ResponseEntity<String> handleBadSortingFieldException(BadSortingFieldException e) {
        var lastUserCodeExecuted = Arrays.stream(e.getStackTrace()).filter(stackTraceElement -> stackTraceElement.getClassName().contains("com.sap.ose.projetose")).findFirst().orElseThrow();
        log.log(Level.WARNING, lastUserCodeExecuted.getClassName() + "." + lastUserCodeExecuted.getMethodName(), e);
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleServiceException(ServiceException e){
        var lastUserCodeExecuted = Arrays.stream(e.getStackTrace()).filter(stackTraceElement -> stackTraceElement.getClassName().contains("com.sap.ose.projetose")).findFirst().orElseThrow();
        log.log(Level.WARNING, lastUserCodeExecuted.getClassName() + "." + lastUserCodeExecuted.getMethodName(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnknownException(Exception e) {
        var lastUserCodeExecuted = Arrays.stream(e.getStackTrace()).filter(stackTraceElement -> stackTraceElement.getClassName().contains("com.sap.ose.projetose")).findFirst().orElseThrow();
        log.log(Level.WARNING, lastUserCodeExecuted.getClassName() + "." + lastUserCodeExecuted.getMethodName(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StageNotFoundException.class)
    public ResponseEntity<String> handleStageNotFoundException(StageNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<String> handleInvalidStateException(InvalidStateException e) {
        var lastUserCodeExecuted = Arrays.stream(e.getStackTrace()).filter(stackTraceElement -> stackTraceElement.getClassName().contains("com.sap.ose.projetose")).findFirst().orElseThrow();
        log.log(Level.WARNING, lastUserCodeExecuted.getClassName() + "." + lastUserCodeExecuted.getMethodName(), e);
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}

