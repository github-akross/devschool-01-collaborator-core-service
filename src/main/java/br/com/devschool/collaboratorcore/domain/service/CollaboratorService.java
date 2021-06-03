package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CollaboratorService {

    ResponseEntity<List<Collaborator>> getAllCollaborator();

    ResponseEntity<Collaborator> getCollaboratorByCpf(Long cpf);


}
