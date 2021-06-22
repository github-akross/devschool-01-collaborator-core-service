package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.dto.BlacklistResponse;
import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.infrastructure.exception.*;
import br.com.devschool.collaboratorcore.infrastructure.repository.CollaboratorRepository;
import br.com.devschool.collaboratorcore.infrastructure.repository.SectorRepository;
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

    @Mock
    private SectorRepository sectorRepository;

    // Mocka a API
    @Mock
    private BlackListApi blackListApi;

    private final String COLLABORATOR_CPF = "123456786652";

    // Define um mock de resposta falsa de api
    private BlacklistResponse mockBlacklistResponseNotDuplicate(){
        return  BlacklistResponse.builder()
                .result(false)
                .build();
    };

    // Define um mock de resposta true de api
    private BlacklistResponse mockBlacklistResponseDuplicate(){
        return  BlacklistResponse.builder()
                .result(true)
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

    // Define um mock de Collaborator muito velho
    private CollaboratorRequest mockOlderCollaboratorRequest(){
        return  CollaboratorRequest.builder()
                .cpf("45632178986")
                .name("Carla")
                .gender("F")
                .birthdate(LocalDate.of(1950, 1, 1))
                .sectorId(1L)
                .build();
    }

    // Define um mock de Collaborador muito jovem
    private CollaboratorRequest mockYoungerCollaboratorRequest(){
        return  CollaboratorRequest.builder()
                .cpf("45632178986")
                .name("Carla")
                .gender("F")
                .birthdate(LocalDate.now())
                .sectorId(1L)
                .build();
    }

    // Define um mock de colaborador
    private CollaboratorRequest mockCollaboratorRequest(){
        return  CollaboratorRequest.builder()
                .cpf("45632178986")
                .name("Carlos")
                .gender("m")
                .birthdate(LocalDate.of(2000, 1,1))
                .sectorId(1L)
                .build();
    }

    // Define um mock de Sector
    private Sector mockSector(){
        return  Sector.builder()
                .id(1L)
                .name("Teste")
                .description("Setor Teste")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    // Teste de CreateCollaborator
    // Criação de colaborador com CPF na lista negra
    @Test(expected = CollaboratorOnBlacklistException.class)
    public void givenCollaboratorOnBlacklistAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        when(blackListApi.getBlacklistByCpf(collaboratorRequest.getCpf())).thenReturn(mockBlacklistResponseDuplicate());
        collaboratorService.createCollaborator(collaboratorRequest);
    }

    // Criação de colaborador com cpf que já existe
    @Test(expected = CollaboratorAlreadyExistsException.class)
    public void givenDuplicateCollaboratorAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        Collaborator collaborator = mockCollaborator();
        when(blackListApi.getBlacklistByCpf(collaboratorRequest.getCpf())).thenReturn(mockBlacklistResponseNotDuplicate());
        when(collaboratorRepository.findByCpf(collaboratorRequest.getCpf())).thenReturn(Optional.of(collaborator));
        collaboratorService.createCollaborator(mockCollaboratorRequest());

        Assert.assertNotNull(collaboratorRequest);
        verify(blackListApi).getBlacklistByCpf(collaboratorRequest.getCpf());
    }

    // Criação de Colaborador com setor que não existe
    @Test(expected = SectorNotFoundException.class)
    public void givenCollaboratorRequestForNonExistentSectorThenThrowSectorNotFoundException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        when(blackListApi.getBlacklistByCpf(collaboratorRequest.getCpf())).thenReturn(mockBlacklistResponseNotDuplicate());
        when(collaboratorRepository.findByCpf(collaboratorRequest.getCpf())).thenReturn(Optional.empty());
        when(sectorRepository.existsById(collaboratorRequest.getSectorId())).thenReturn(false);
        collaboratorService.createCollaborator(collaboratorRequest);
    }

    // Criação de colaborador masculino acima de 30%
    @Test(expected = CollaboratorExceedsMaleGenderPercentageException.class)
    public void givenCollaboratorRequestExceedsSectorMalePercentage() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        when(blackListApi.getBlacklistByCpf(collaboratorRequest.getCpf())).thenReturn(mockBlacklistResponseNotDuplicate());
        when(collaboratorRepository.findByCpf(collaboratorRequest.getCpf())).thenReturn(Optional.empty());
        when(sectorRepository.existsById(collaboratorRequest.getSectorId())).thenReturn(true);
        when(sectorRepository.calculateMalePercentageBySector(collaboratorRequest.getSectorId())).thenReturn(42F);

        collaboratorService.createCollaborator(collaboratorRequest);
    }

    // Teste do Update Collaborator
    @Test(expected = CollaboratorNotFoundException.class)
    public void givenCollaboratorCpfThatNotExistsAssertException() {
        CollaboratorRequest collaboratorRequest = mockCollaboratorRequest();
        Sector mockSector = mockSector();
        when(sectorRepository.findById(collaboratorRequest.getSectorId())).thenReturn(Optional.of(mockSector));
        when(collaboratorRepository.findByCpf(COLLABORATOR_CPF)).thenReturn(Optional.empty());
        collaboratorService.updateCollaboratorByCpf(COLLABORATOR_CPF, collaboratorRequest);
    }
}
