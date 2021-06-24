package br.com.devschool.collaboratorcore.application.controller;

import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CollaboratorController {
    private final CollaboratorService collaboratorService;

    // chamadas das rotas do CRUD de Collaborator

    //  get: Listar todos os colaboradores
    @GetMapping("/collaborator")
    public ResponseEntity<List<Collaborator>> getAllCollaborator() {
        return ResponseEntity.ok(collaboratorService.getAllCollaborators());
    }

    //  get: Listar 1 colaborador pelo cpf
    @GetMapping("/collaborator/{cpf}")
    public ResponseEntity<Collaborator> getCollaboratorByCpf(@PathVariable String cpf) {
        if(cpf.isEmpty()){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(collaboratorService.getCollaboratorByCpf(cpf));
    }

    //  post: Criacao de colaborador
    @PostMapping("/collaborator")
    public ResponseEntity<Collaborator> createCollaborator (@RequestBody CollaboratorRequest collaboratorRequest) throws JsonProcessingException {
        return ResponseEntity.ok(collaboratorService.createCollaborator(collaboratorRequest));
    }

    //  put: Atualizar colaborador pelo cpf
    @PutMapping("/collaborator/{cpf}")
    public ResponseEntity<Collaborator> updateCollaboratorByCpf(@PathVariable String cpf, @RequestBody CollaboratorRequest collaboratorRequest) {
        return ResponseEntity.ok(collaboratorService.updateCollaboratorByCpf(cpf,collaboratorRequest));
    }

    //  delete: Deletar Collaborator pelo cpf
    @DeleteMapping("/collaborator/{cpf}")
    public ResponseEntity deleteCollaboratorByCpf(@PathVariable String cpf) {
        collaboratorService.deleteCollaboratorByCpf(cpf);
        return ResponseEntity.ok().build();
    }
}

