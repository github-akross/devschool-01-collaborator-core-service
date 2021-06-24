package br.com.devschool.collaboratorcore.infrastructure.exception;


public class SectorNotFoundException extends RuntimeException {
    private  String name;

    public SectorNotFoundException(String name){
        super(String.format("O Sector %s nao encontrado" , name));
        this.name = name;
    }
}
