package br.com.devschool.collaboratorcore.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlackList {
    private  Long id;
    private String cpf ;
    private LocalDateTime createdDate;
}
