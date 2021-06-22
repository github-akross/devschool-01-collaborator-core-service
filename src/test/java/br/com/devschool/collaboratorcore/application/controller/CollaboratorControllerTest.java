package br.com.devschool.collaboratorcore.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import br.com.devschool.collaboratorcore.infrastructure.exception.CollaboratorAlreadyExistsException;
import br.com.devschool.collaboratorcore.infrastructure.exception.CollaboratorNotFoundException;
import br.com.devschool.collaboratorcore.infrastructure.repository.api.BlackListApi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorControllerTest {

    // Injeta os mocks no controller
    @InjectMocks
    private CollaboratorController controller;

    // Mocka o serviço
    @Mock
    private CollaboratorService collaboratorService;

    // Mocka a API
    @Mock
    private BlackListApi blackListApi;

    private final String COLLABORATOR_CPF = "123456786652";

    @Test(expected = CollaboratorNotFoundException.class)
    public void givenCollaboratorCpfThatNotExistThenThrowsCollaboratorNotFoundException() {
        doThrow(CollaboratorNotFoundException.class).when(collaboratorService).getCollaboratorByCpf(any());
        ResponseEntity<Collaborator> result = controller.getCollaboratorByCpf(COLLABORATOR_CPF);
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenCollaboratorCpfIsBlank() {
       ResponseEntity<Collaborator> result = controller.getCollaboratorByCpf("");
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR ,result.getStatusCode());
    }

    @Test(expected = CollaboratorAlreadyExistsException.class)
    public void givenCollaboratorThatExist() {
        doThrow(CollaboratorAlreadyExistsException.class).when(collaboratorService).createCollaborator(new CollaboratorRequest("45632178986", 1L, "Carla", "F", LocalDate.now()));
        ResponseEntity<Collaborator> result = controller.createCollaborator(new CollaboratorRequest("45632178996", 1L, "Carla", "F", LocalDate.now()));
        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}