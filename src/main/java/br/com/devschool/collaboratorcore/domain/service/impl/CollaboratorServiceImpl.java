package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import br.com.devschool.collaboratorcore.infrastructure.exception.*;
import br.com.devschool.collaboratorcore.infrastructure.repository.CollaboratorRepository;
import br.com.devschool.collaboratorcore.infrastructure.repository.SectorRepository;
import br.com.devschool.collaboratorcore.infrastructure.repository.api.BlackListApi;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CollaboratorServiceImpl implements CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;
    private final SectorRepository sectorRepository;
    private final BlackListApi blackListApi;

    @Override
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }


    /*messagem de error pelo Errorhandler que mostra que o cpf nao foi cadastrado*/
    @Override
    public Collaborator getCollaboratorByCpf(String cpf) {
        return collaboratorRepository.findByCpf(cpf).orElseThrow( () -> new CollaboratorNotFoundException(cpf));
    }

    @Override
    public Collaborator createCollaborator(CollaboratorRequest collaboratorRequest) {
        LocalDate birthdate = collaboratorRequest.getBirthdate();
        Period period = Period.between(birthdate, LocalDate.now());

        // ordem de servico

        //  Não é possivel cadastrar um colaborador que está na blacklist - CollaboratorOnBlacklistException
        if(blackListApi.getBlacklistByCpf(collaboratorRequest.getCpf()).isResult()){
            throw new CollaboratorOnBlacklistException(collaboratorRequest.getCpf());
        }

        // Não é possivel cadastrar um colaborador com um setor inválido
        if(Objects.isNull(collaboratorRequest.getSectorId()) || !sectorRepository.existsById(collaboratorRequest.getSectorId())){
            throw new RuntimeException();
        }

        Sector sector = sectorRepository.getById(collaboratorRequest.getSectorId());

        // Não é possivel cadastrar um colaborador com o mesmo cpf - CollaboratorAlreadyExistsException
        if (collaboratorRepository.findByCpf(collaboratorRequest.getCpf()).isPresent()) {
            throw new CollaboratorAlreadyExistsException(collaboratorRequest.getCpf());
        }


        //  Não é possível cadastrar colaboradores menores de idade ou acima de 60 anos - CollaboratorInvalidBirthdayException
        if (18 > period.getYears() || period.getYears() > 60) {
            throw new CollaboratorInvalidBirthdayException(period.getYears());
        }

        //  Mão é possível ter mais do que 30% de colaboradores do sexo masculino - CollaboratorExceedsMaleGenderPercentageException
        float malePercentage =  sectorRepository.calculateMalePercentageBySector(collaboratorRequest.getSectorId());
        if ("m".equals(collaboratorRequest.getGender()) && malePercentage > 30.0) {
            throw new CollaboratorExceedsMaleGenderPercentageException(malePercentage);

        }

        Collaborator collaborator = Collaborator.builder()
                .birthdate(birthdate)
                .createdDate(LocalDateTime.now())
                .cpf(collaboratorRequest.getCpf())
                .gender(collaboratorRequest.getGender())
                .name(collaboratorRequest.getName())
                .updatedDate(LocalDateTime.now())
                .sector(sector)
                .build();

        return collaboratorRepository.save(collaborator);

    }

    @Override
    public Collaborator updateCollaboratorByCpf(String cpf, CollaboratorRequest collaboratorRequest) {
        // Verifica se o setor informado existe
        Optional<Sector> sectorOptional = sectorRepository.findById(collaboratorRequest.getSectorId());

        if (!sectorOptional.isPresent()) {
            throw new RuntimeException();
        }

        Sector sectorExistent = sectorOptional.get();

        // Consulta se o collaborador existe

        Optional<Collaborator> collaboratorOptional = collaboratorRepository.findByCpf(cpf);

        if (!collaboratorOptional.isPresent()) {
            throw new RuntimeException();
        }

        Collaborator collaboratorExistent = collaboratorOptional.get();

        return collaboratorRepository.save(Collaborator.builder()
                .id(collaboratorExistent.getId())
                .name(collaboratorRequest.getName())
                .cpf(collaboratorRequest.getCpf())
                .gender(collaboratorRequest.getGender())
                .birthdate(collaboratorRequest.getBirthdate())
                .sector(sectorExistent)
                .createdDate(collaboratorExistent.getCreatedDate())
                .updatedDate(LocalDateTime.now())
                .build());
    }

    @Override
    @Transactional
    public void deleteCollaboratorByCpf(String cpf) {
        collaboratorRepository.deleteByCpf(cpf);
    }
}