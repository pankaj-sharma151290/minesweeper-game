package com.ing.assignment.exception;

import com.ing.assignment.model.resourceobject.MinesweeperExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MinesweeperExceptionAdvice extends ResponseEntityExceptionHandler {
    /**
     * This advice will redirect all the Exception occurred in the application to this method
     *
     * @param exception
     * @return MinesweeperExceptionResponse
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MinesweeperExceptionResponse> resolveAndWriteException(Exception exception) {

        MinesweeperExceptionResponse response = new MinesweeperExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(), exception.getMessage());
        logger.error("Error caught in MinesweeperExceptionAdvice", exception);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * This advice will redirect all the TMSException occurred in the application to this method
     *
     * @param exception
     * @return MinesweeperExceptionResponse
     */
    @ExceptionHandler(MinesweeperException.class)
    public ResponseEntity<MinesweeperExceptionResponse> resolveAndWriteException(MinesweeperException exception) {

        MinesweeperExceptionResponse response = new MinesweeperExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(), exception.getMessage());
        logger.error("Error caught in MinesweeperExceptionAdvice", exception);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}