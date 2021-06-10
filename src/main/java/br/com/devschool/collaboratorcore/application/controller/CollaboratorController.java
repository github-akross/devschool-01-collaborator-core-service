package br.com.devschool.collaboratorcore.application.controller;

import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CollaboratorController {
    private final CollaboratorService collaboratorService;

    //  get: listagem de todos os colaboradores
    @GetMapping("/collaborator")
    public ResponseEntity<List<Collaborator>> getAllCollaborator() {
        return ResponseEntity.ok(collaboratorService.getAllCollaborators());
    }

    //  get: lista 1 colaborador pelo cpf
    @GetMapping("/collaborator/{cpf}")
    public ResponseEntity<Collaborator> getCollaboratorByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(collaboratorService.getCollaboratorByCpf(cpf));
    }

    //  post: Criacao de colaborador
    @PostMapping("/collaborator")
    public ResponseEntity<Collaborator> saveCollaborator(@RequestBody CollaboratorRequest collaboratorRequest) {
        return ResponseEntity.ok(collaboratorService.createCollaborator(collaboratorRequest));
    }

    //  Atualizar colaborador pelo cpf
    @PutMapping("/collaborator/{cpf}")
    public ResponseEntity<Collaborator> updateCollaboratorByCpf(@PathVariable String cpf, @RequestBody Collaborator collaborator) {
        return ResponseEntity.ok(collaboratorService.updateCollaboratorByCpf(cpf,collaborator));
    }


    //  Deletar Collaborator pelo cpf
    @DeleteMapping("/collaborator/{cpf}")
    public ResponseEntity deleteCollaboratorByCpf(@PathVariable String cpf) {
        collaboratorService.deleteCollaboratorByCpf(cpf);
        return ResponseEntity.ok().build();
    }
}

