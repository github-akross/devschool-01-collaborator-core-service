package br.com.devschool.collaboratorcore.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import br.com.devschool.collaboratorcore.infrastructure.exception.CollaboratorNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorControllerTest {

    @InjectMocks
    private CollaboratorController controller;

    @Mock
    private CollaboratorService collaboratorService;

    private final String COLLABORATOR_CPF = "COLLABORATOR_CPF";

    @Test(expected = CollaboratorNotFoundException.class)
    public void givenCollaboratorCpfThatNotExistThenThrowsCollaboratorNotFoundException() {
        doThrow(CollaboratorNotFoundException.class).when(collaboratorService).getCollaboratorByCpf(any());
        ResponseEntity<Collaborator> result = controller.getCollaboratorByCpf(COLLABORATOR_CPF);
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
