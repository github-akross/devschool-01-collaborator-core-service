package br.com.devschool.collaboratorcore.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import br.com.devschool.collaboratorcore.infrastructure.exception.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorControllerTest {

    // Injeta os mocks no controller
    @InjectMocks
    private CollaboratorController controller;

    // Mocka o serviço
    @Mock
    private CollaboratorService collaboratorService;

    // Define um mock de Collaborator
    private Collaborator mockCollaborator(){
        return Collaborator.builder()
                .id(1L)
                .cpf("45632178986")
                .name("Carla")
                .gender("f")
                .birthdate(LocalDate.of(1990, 1, 1))
                .sector(new Sector(1L , "TI", "Setor de TI", LocalDateTime.now(), LocalDateTime.now()))
                .build();
    }

    // Define um mock de CollaboratorRequest
    private CollaboratorRequest mockCollaboratorRequest(){
        return  CollaboratorRequest.builder()
                .cpf("45632178986")
                .name("Carla")
                .gender("f")
                .birthdate(LocalDate.of(1990, 1, 1))
                .sectorId(1L)
                .build();
    }

    private final String COLLABORATOR_CPF = "45632178986";

    // Testes da rota getByCpf
    @Test
    public void givenCollaboratorCpfThatExistReturnCollaborator() {
        Collaborator collaborator = mockCollaborator();
        doReturn(collaborator).when(collaboratorService).getCollaboratorByCpf(any());

        ResponseEntity<Collaborator> result = controller.getCollaboratorByCpf(COLLABORATOR_CPF);

        Assert.assertEquals(collaborator, result.getBody());
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test(expected = CollaboratorNotFoundException.class)
    public void givenCollaboratorCpfThatNotExistThenThrowsCollaboratorNotFoundException() {
        doThrow(CollaboratorNotFoundException.class).when(collaboratorService).getCollaboratorByCpf(any());

        ResponseEntity<Collaborator> result = controller.getCollaboratorByCpf(COLLABORATOR_CPF);

        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenCollaboratorCpfIsBlank() {
        ResponseEntity<Collaborator> result = controller.getCollaboratorByCpf("");

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    // Testes da rota createCollaborator
    @Test
    public void givenCollaboratorRequestReturnCollaborator() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        Collaborator collaborator = mockCollaborator();
        doReturn(collaborator).when(collaboratorService).createCollaborator(collaboratorRequest);

        ResponseEntity<Collaborator> result = controller.createCollaborator(collaboratorRequest);

        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(collaborator, result.getBody());
    }

    @Test(expected = CollaboratorOnBlacklistException.class)
    public void givenCollaboratorThatExistOnBlacklistAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        doThrow(CollaboratorOnBlacklistException.class).when(collaboratorService).createCollaborator(any());

        ResponseEntity<Collaborator> result = controller.createCollaborator(collaboratorRequest);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test(expected = CollaboratorAlreadyExistsException.class)
    public void givenCollaboratorThatExistAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        doThrow(CollaboratorAlreadyExistsException.class).when(collaboratorService).createCollaborator(any());

        ResponseEntity<Collaborator> result = controller.createCollaborator(collaboratorRequest);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test(expected = SectorNotFoundException.class)
    public void givenCollaboratorWithInvalidSectorIdAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        doThrow(SectorNotFoundException.class).when(collaboratorService).createCollaborator(any());

        ResponseEntity<Collaborator> result = controller.createCollaborator(collaboratorRequest);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test(expected = CollaboratorExceedsMaleGenderPercentageException.class)
    public void givenCollaboratorThatExceedsMaleGenderPercentageAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        doThrow(CollaboratorExceedsMaleGenderPercentageException.class).when(collaboratorService).createCollaborator(any());

        ResponseEntity<Collaborator> result = controller.createCollaborator(collaboratorRequest);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test(expected = CollaboratorInvalidBirthdayException.class)
    public void givenCollaboratorThatHaveInvalidBirthdayAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        doThrow(CollaboratorInvalidBirthdayException.class).when(collaboratorService).createCollaborator(any());

        ResponseEntity<Collaborator> result = controller.createCollaborator(collaboratorRequest);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    // Testes da rota updateCollaborator
    @Test
    public void givenCollaboratorUpdateReturnUpdatedCollaborator() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        Collaborator collaborator = mockCollaborator();
        doReturn(collaborator).when(collaboratorService).updateCollaboratorByCpf(any(), any());

        ResponseEntity<Collaborator> result = controller.updateCollaboratorByCpf(COLLABORATOR_CPF, collaboratorRequest);

        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(collaborator, result.getBody());
    }

    @Test(expected = CollaboratorNotFoundException.class)
    public void givenCollaboratorCpfThatNotExistOnUpdateAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        doThrow(CollaboratorNotFoundException.class).when(collaboratorService).updateCollaboratorByCpf(any(), any());

        ResponseEntity<Collaborator> result = controller.updateCollaboratorByCpf(COLLABORATOR_CPF, collaboratorRequest);
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test(expected = SectorNotFoundException.class)
    public void givenCollaboratorSectorIdThatNotExistOnUpdateAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        doThrow(SectorNotFoundException.class).when(collaboratorService).updateCollaboratorByCpf(any(), any());

        ResponseEntity<Collaborator> result = controller.updateCollaboratorByCpf(COLLABORATOR_CPF, collaboratorRequest);
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    // Testes da rota deleteCollaborator

}
