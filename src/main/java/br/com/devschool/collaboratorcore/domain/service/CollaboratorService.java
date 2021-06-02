package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboratorService extends JpaRepository<Collaborator , Long> {
}
