package br.com.devschool.collaboratorcore.infrastructure.exception;

public class CollaboratorExceedsMaleGenderPercentageException extends  RuntimeException {
    private float percentage;

    public CollaboratorExceedsMaleGenderPercentageException(float percentage){
        super(String.format("A porcentagem de %f do colaboradores do sexo masculino ultrapassou o limite de 30 porcento ", percentage));
        this.percentage = percentage ;
    }
}
