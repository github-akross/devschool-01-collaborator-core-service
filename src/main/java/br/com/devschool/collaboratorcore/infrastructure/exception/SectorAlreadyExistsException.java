package br.com.devschool.collaboratorcore.infrastructure.exception;

public class SectorAlreadyExistsException  extends RuntimeException{
    private String name;

    public  SectorAlreadyExistsException(String name){
        super(String.format("O setor %s ja esta cadastrado", name));
        this.name = name;
    }
}
