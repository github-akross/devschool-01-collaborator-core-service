package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorCpfPassedTheNumberSizeException extends  RuntimeException {
    private  String cpf ;

    public CollaboratorCpfPassedTheNumberSizeException(String cpf) {
        super(String.format("O cpf %s que voce tentou cadastrar passou o tamanho de 11 digitos ", cpf));
        this.cpf = cpf;
    }
}
