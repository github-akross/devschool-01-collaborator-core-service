package br.com.devschool.collaboratorcore.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Table(name = "Sector")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
}
