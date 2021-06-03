package br.com.devschool.collaboratorcore.application.controller;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import br.com.devschool.collaboratorcore.infrastructure.repository.CollaboratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CollaboratorController {

    private  final CollaboratorService collaboratorService;

    //get: listagem de todos os colaboradores
    @GetMapping("/collaborator")
    public ResponseEntity<List<Collaborator>> getAllCollaborator() {
        return collaboratorService.getAllCollaborator();
    }

    //get: lista 1 colaborador pelo cpf
    @GetMapping("/collaborator/{cpf}")
    public ResponseEntity<Collaborator> getCollaboratorByCpf(@PathVariable Long cpf) {
        return collaboratorService.getCollaboratorByCpf(cpf);

    }

//    //post: Criacao de colaborador
//    @PostMapping("/collaborator")
//    public Collaborator saveCollaborator(@RequestBody Collaborator collaborator) {
//        return repository.save(collaborator);


    }

