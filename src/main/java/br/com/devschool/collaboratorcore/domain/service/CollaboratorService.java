package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;

import java.util.List;

public interface CollaboratorService {

    // Assinatura dos serviços de Collaborator

    List<Collaborator> getAllCollaborators(); // listagem de todos os colaboradores

    Collaborator getCollaboratorByCpf(String cpf); // listagem de um colaborador pelo cpf

    Collaborator createCollaborator(CollaboratorRequest collaboratorRequest); // criação dos colaboradores

    Collaborator updateCollaboratorByCpf(String cpf, CollaboratorRequest collaboratorRequest); // atualização dos colaboradores pelo cpf

    void deleteCollaboratorByCpf(String cpf); // exclusão dos colaboradores pelo cpf
}
