package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorAlreadyExistsException  extends  RuntimeException{
    private  String cpf ;

    public CollaboratorAlreadyExistsException(String cpf){
        super(String.format("Colaborador com o cpf %s ja esta cadastrado no sistema", cpf));
        this.cpf = cpf ;
    }
}
