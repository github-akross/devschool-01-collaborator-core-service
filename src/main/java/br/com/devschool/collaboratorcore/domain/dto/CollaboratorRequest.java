package br.com.devschool.collaboratorcore.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CollaboratorRequest {
    private Long id;
    private String cpf;
    private Long sectorId;
    private String name;
    private String gender;
    private LocalDate birthdate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
