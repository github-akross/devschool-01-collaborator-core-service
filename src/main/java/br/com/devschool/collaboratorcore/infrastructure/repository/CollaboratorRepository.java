package br.com.devschool.collaboratorcore.infrastructure.repository;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

}
