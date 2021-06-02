package br.com.devschool.collaboratorcore.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Table(name = "Collaborator")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long cpf;
    @ManyToOne
    Sector sector ;
    String name;
    String gender;
    LocalDateTime birthdate ;
    LocalDateTime created_date;
    LocalDateTime updated_date;
}