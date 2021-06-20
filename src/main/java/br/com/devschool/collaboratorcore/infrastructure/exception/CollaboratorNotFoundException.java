package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorNotFoundException extends RuntimeException {
    private String cpf;

    public CollaboratorNotFoundException(String cpf){
        super(String.format("collaborator com cpf %s nao encontrada", cpf));
        this.cpf = cpf ;
    }

}
