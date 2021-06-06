package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CollaboratorService {

    ResponseEntity<List<Collaborator>> getAllCollaborator(); //listagem de todos os colaoradores

    ResponseEntity<Collaborator> getCollaboratorByCpf(Long cpf); //listagem dos colaoradores pelo cpf

    ResponseEntity<Collaborator> updateCollaboratorbyCpf(Long cpf); // Atulizacao dos colaoradores

    //ResponseEntity<Collaborator> deleteCollaboratorbyCpf(Long cpf);


}
