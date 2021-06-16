package br.com.devschool.collaboratorcore.application.controller;

import br.com.devschool.collaboratorcore.infrastructure.exception.CollaboratorNotFoundException;
import br.com.devschool.collaboratorcore.infrastructure.exception.SectorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CollaboratorNotFoundException.class)
    public ResponseEntity<String> collaboratorNotFoundException(CollaboratorNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    //Sector
    @ExceptionHandler(SectorNotFoundException.class)
    public  ResponseEntity<String> sectorNotFoundException(SectorNotFoundException e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
