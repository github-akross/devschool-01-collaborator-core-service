package br.com.devschool.collaboratorcore.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class CollaboratorRequest {
    private String cpf;
    private Long sectorId;
    private String name;
    private String gender;
    private LocalDate birthdate;

}
