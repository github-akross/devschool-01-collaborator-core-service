package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import br.com.devschool.collaboratorcore.infrastructure.repository.CollaboratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CollaboratorServiceImpl implements CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;

    @Override
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    @Override
    public ResponseEntity<Collaborator> getCollaboratorByCpf(Long cpf) {
        return null;
    }

    @Override
    public ResponseEntity<Collaborator> updateCollaboratorbyCpf(Long cpf) {
        return null;
    }
}
