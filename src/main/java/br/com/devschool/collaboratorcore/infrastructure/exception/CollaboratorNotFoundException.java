package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorNotFoundException extends RuntimeException {
    private String cpf;

    public CollaboratorNotFoundException(String cpf){
        super(String.format("Colaborador com o cpf %s nao encontrado", cpf));
        this.cpf = cpf ;
    }

}
