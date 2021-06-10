package br.com.devschool.collaboratorcore.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(name = "collaborator")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpf;
    @ManyToOne
    private Sector sector;
    private String name;
    private String gender;
    private LocalDate birthdate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
