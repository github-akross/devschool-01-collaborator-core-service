package br.com.devschool.collaboratorcore.application.controller;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.infrastructure.exception.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
   // Tratativa  de erro do RuntimeException - joga status 500 para o body

    // Collaborator - mensagem de error
    @ExceptionHandler(CollaboratorNotFoundException.class)
    public ResponseEntity<String> collaboratorNotFoundException(CollaboratorNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // status que vai passar no body da requisicao
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

//    @ExceptionHandler(CollaboratorCpfPassedTheNumberSizeException.class)
//    public  ResponseEntity<String> CollaboratorCpfPassedTheNumberSizeException(CollaboratorCpfPassedTheNumberSizeException e){
//        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//
//    }

    // Blacklist - mensagem de error
    @ExceptionHandler(CollaboratorOnBlacklistException.class)
    public  ResponseEntity<String> CollaboratorOnBlacklistException(CollaboratorOnBlacklistException e){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // Sector - mensagem de error
    @ExceptionHandler(SectorNotFoundException.class)
    public  ResponseEntity<String> sectorNotFoundException(SectorNotFoundException e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(SectorAlreadyExistsException.class)
    public ResponseEntity<String> SectorAlreadyExistsException(SectorAlreadyExistsException e){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
