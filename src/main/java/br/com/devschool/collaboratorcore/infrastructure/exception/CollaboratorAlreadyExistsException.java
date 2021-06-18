package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorAlreadyExistsException  extends  RuntimeException{
    private  String cpf ;

    public CollaboratorAlreadyExistsException(String cpf){
        super(String.format("collaborator com cpf %s ja esta cadastrado", cpf));
        this.cpf = cpf ;
    }
}
