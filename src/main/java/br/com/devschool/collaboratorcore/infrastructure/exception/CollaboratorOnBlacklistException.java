package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorOnBlacklistException extends  RuntimeException{
    private String cpf;

    public CollaboratorOnBlacklistException(String cpf){
        super(String.format("O colaborador com o cpf %s esta na blacklist", cpf));
        this.cpf = cpf ;
    }

}
