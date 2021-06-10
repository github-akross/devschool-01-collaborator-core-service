package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CollaboratorService {

    List<Collaborator> getAllCollaborators(); //listagem de todos os colaboradores

    Collaborator getCollaboratorByCpf(String cpf); //listagem dos colaboradores pelo cpf

    Collaborator createCollaborator(CollaboratorRequest collaborator); //criação dos colaboradores

    Collaborator updateCollaboratorByCpf(String cpf, Collaborator collaborator); //atualização dos colaboradores pelo cpf

    void deleteCollaboratorByCpf(String cpf); //deletando colaboradores pelo cpf
}
