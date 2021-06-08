package br.com.devschool.collaboratorcore.application.controller;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CollaboratorController {
    private final CollaboratorService collaboratorService;

    //get: listagem de todos os colaboradores
    @GetMapping("/collaborator")
    public List<Collaborator> getAllCollaborator() {

        return collaboratorService.getAllCollaborators();
    }

    //get: lista 1 colaborador pelo cpf
    @GetMapping("/collaborator/{cpf}")
    public ResponseEntity<Collaborator> getCollaboratorByCpf(@PathVariable String cpf) {
        return collaboratorService.getCollaboratorByCpf(cpf);

    }

    //  Atualizar colaborador pelo cpf
    @PatchMapping("/collaborator/{cpf}")
    public  ResponseEntity<Collaborator> updateCollaboratorbyCpf(@RequestBody Long cpf){
        return collaboratorService.updateCollaboratorbyCpf(cpf) ;
    }



//    //deletar Collaborator pelo cpf
//    @DeleteMapping("/collaborator/{cpf}")
//    public ResponseEntity<Collaborator> deleteCollaboratorbyCpf(@PathVariable Long cpf){
//        return collaboratorService.deleteCollaboratorbyCpf(cpf) ;
//    }


//    post: Criacao de colaborador
//    @PostMapping("/collaborator")
//    public Collaborator saveCollaborator(@RequestBody Collaborator collaborator) {
//        return collaboratorService.saveCollaborator(cpf);


    }

