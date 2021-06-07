package br.com.devschool.collaboratorcore.infrastructure.repository;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {
    List<Collaborator> findAll();
}
