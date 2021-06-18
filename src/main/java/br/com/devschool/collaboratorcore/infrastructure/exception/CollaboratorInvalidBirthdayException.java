package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorInvalidBirthdayException extends  RuntimeException {
    private  int years;

    public CollaboratorInvalidBirthdayException(int years){
        super(String.format("A idade de %d do colaborador deve esta entre 18 e 60 anos", years));
        this.years = years ;
    }
}
