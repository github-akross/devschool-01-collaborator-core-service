package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorInvalidBirthdayException extends  RuntimeException {
    private  int years;

    public CollaboratorInvalidBirthdayException(int years){
        super(String.format("Nao foi possivel cadastrar o colaborador com a idade %d, ela precisa estar entre 18 e 60 anos ", years));
        this.years = years ;
    }
}
