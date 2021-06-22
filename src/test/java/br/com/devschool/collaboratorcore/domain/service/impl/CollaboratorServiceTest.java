package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.dto.BlacklistResponse;
import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.infrastructure.repository.CollaboratorRepository;
import br.com.devschool.collaboratorcore.infrastructure.repository.api.BlackListApi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorServiceTest {

    // Injeta os mocks no serviço
    @InjectMocks
    private CollaboratorServiceImpl collaboratorService;

    // Mocka o repositório
    @Mock
    private CollaboratorRepository collaboratorRepository;
    private Object CollaboratorAlreadyExistsException;

    // Mocka a API
    @Mock
    private BlackListApi blackListApi;

    // Define um mock de resposta falsa de api
    private BlacklistResponse mockBlacklistResponse(){
        return  BlacklistResponse.builder()
                .result(false)
                .build();
    };

    // Define um mock de Collaborator
    private Collaborator mockCollaborator(){
        return Collaborator.builder()
                .id(1L)
                .cpf("45632178986")
                .name("Carla")
                .gender("F")
                .birthdate(LocalDate.now())
                .sector(new Sector(1L , "TI", "Setor de TI", LocalDateTime.now(), LocalDateTime.now()))
                .build();
    }

    // Define um mock de CollaboratorRequest
    private CollaboratorRequest mockCollaboratorRequest(){
        return  CollaboratorRequest.builder()
                .cpf("45632178986")
                .name("Carla")
                .gender("F")
                .birthdate(LocalDate.now())
                .sectorId(1L)
                .build();
    }

    @Test(expected = br.com.devschool.collaboratorcore.infrastructure.exception.CollaboratorAlreadyExistsException.class)
    public void givenDuplicateCollaboratorAssertException() {
        Collaborator collaborator = mockCollaborator();
        when(blackListApi.getBlacklistByCpf(collaborator.getCpf())).thenReturn(mockBlacklistResponse());
        when(collaboratorRepository.findByCpf(collaborator.getCpf())).thenReturn(Optional.of(collaborator));
        collaboratorService.createCollaborator(mockCollaboratorRequest());
        Assert.assertNotNull(collaborator);
        verify(collaboratorService).createCollaborator(mockCollaboratorRequest());
    }
}
