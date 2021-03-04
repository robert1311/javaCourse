/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.service;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author rober
 */
@ControllerAdvice
@RestController
public class BullsAndCowsExceptionHandler 
        extends ResponseEntityExceptionHandler {
    
    private static final String CONSTRAINT_MESSAGE = "Could not save your item. "
            + "Please ensure it is valid and try again.";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    private String INVALID_ENTRY = "Invalid entry for guess. Try again";
    
    @ExceptionHandler(InvalidGuessException.class)
    public final ResponseEntity<Error> handleInvalidGuessException(
        InvalidGuessException e, WebRequest request){
        
        Error err = new Error();
        err.setMessage(INVALID_ENTRY);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    private String GAME_FINISHED = "Game already finished. Try another game.";
    
    @ExceptionHandler(GameFinishedException.class)
    public final ResponseEntity<Error> handleGameFinishedException(
        GameFinishedException e, WebRequest request){
        
        Error err = new Error();
        err.setMessage(GAME_FINISHED);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    private String NULL_GAME ="Game does not exist. Try another game";
    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<Error> handleNullPointerException(
        NullPointerException e, WebRequest request){
        
        Error err = new Error();
        err.setMessage(NULL_GAME);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    
}
