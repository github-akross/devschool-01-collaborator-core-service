package br.com.devschool.collaboratorcore.application.controller;

import br.com.devschool.collaboratorcore.infrastructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    //colaboadores
    @ExceptionHandler(CollaboratorNotFoundException.class)
    public ResponseEntity<String> collaboratorNotFoundException(CollaboratorNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CollaboratorAlreadyExistsException.class)
    public  ResponseEntity<String> CollaboratorAlreadyExistsException(CollaboratorAlreadyExistsException e){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(CollaboratorInvalidBirthdayException.class)
    public  ResponseEntity<String> CollaboratorInvalidBirthdayException(CollaboratorInvalidBirthdayException e){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CollaboratorExceedsMaleGenderPercentageException.class)
    public  ResponseEntity<String> CollaboratorInvalidBirthdayException(CollaboratorExceedsMaleGenderPercentageException e){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    //blacklist
    @ExceptionHandler(CollaboratorOnBlacklistException.class)
    public  ResponseEntity<String> CollaboratorOnBlacklistException(CollaboratorOnBlacklistException e){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    //Sector
    @ExceptionHandler(SectorNotFoundException.class)
    public  ResponseEntity<String> sectorNotFoundException(SectorNotFoundException e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }



}
