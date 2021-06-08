package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CollaboratorService {

    List<Collaborator> getAllCollaborators(); //listagem de todos os colaboradores

    ResponseEntity<Collaborator> getCollaboratorByCpf(String cpf); //listagem dos colaboradores pelo cpf

    ResponseEntity<Collaborator> updateCollaboratorbyCpf(Long cpf); // atualização dos colaboradores pelo cpf

    //ResponseEntity<Collaborator> deleteCollaboratorbyCpf(Long cpf);


}
