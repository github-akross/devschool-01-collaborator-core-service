package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.dto.BlacklistResponse;
import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.infrastructure.exception.CollaboratorAlreadyExistsException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorServiceTest {

    @InjectMocks
    private CollaboratorServiceImpl collaboratorService;

    @Mock
    private CollaboratorRepository collaboratorRepository;
    private Object CollaboratorAlreadyExistsException;

    @Mock
    private BlackListApi blackListApi;


    private BlacklistResponse mockBlacklistResponse(){
        return  BlacklistResponse.builder()
                .result(false)
                .build();
    };

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
