package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.dto.BlackList;
import br.com.devschool.collaboratorcore.domain.dto.CollaboratorRequest;
import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
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

    @Override
    public Collaborator getCollaboratorByCpf(String cpf) {
        return collaboratorRepository.findByCpf(cpf).orElseThrow(RuntimeException::new);
    }

    @Override
    public Collaborator createCollaborator(CollaboratorRequest collaboratorRequest) {
        LocalDate birthdate = collaboratorRequest.getBirthdate();
        Period period = Period.between(birthdate, LocalDate.now());

        if(Objects.isNull(collaboratorRequest.getSectorId()) || !sectorRepository.existsById(collaboratorRequest.getSectorId())){
            throw new RuntimeException();
        }

        Sector sector = sectorRepository.getById(collaboratorRequest.getSectorId());


        //  Checar se o colaborador está na blacklist
        BlackList blacklistExists = blackListApi.getBlacklistByCpf(collaboratorRequest.getCpf());
        if (!Objects.isNull(blacklistExists)) {
            throw new RuntimeException();
        }

        if (collaboratorRepository.findByCpf(collaboratorRequest.getCpf()).isPresent()) {
            throw new RuntimeException();
        }


        //  Não é possível cadastrar colaboradores menores de idade ou acima de 60 anos
        if (18 > period.getYears() || period.getYears() > 60) {
            throw new RuntimeException();
        }

        //  Mão é possível ter mais do que 30% de colaboradores do sexo masculino
        if ("m".equals(collaboratorRequest.getGender()) && sectorRepository.calculateMalePercentageBySector(collaboratorRequest.getSectorId()) > 30.0) {
            throw new RuntimeException();
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
    public Collaborator updateCollaboratorByCpf(String cpf, Collaborator collaborator) {
        return null;
    }

    @Override
    @Transactional
    public void deleteCollaboratorByCpf(String cpf) {
        collaboratorRepository.deleteByCpf(cpf);
    }
}