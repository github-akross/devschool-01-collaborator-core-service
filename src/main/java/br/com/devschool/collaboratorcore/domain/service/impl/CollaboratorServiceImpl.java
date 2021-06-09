package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import br.com.devschool.collaboratorcore.infrastructure.repository.CollaboratorRepository;
import br.com.devschool.collaboratorcore.infrastructure.repository.SectorRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CollaboratorServiceImpl implements CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;
    private final SectorRepository sectorRepository;

    @Override
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    @Override
    public Collaborator getCollaboratorByCpf(String cpf) {
        return collaboratorRepository.findByCpf(cpf).orElseThrow(RuntimeException::new);
    }

    @Override
    public Collaborator updateCollaboratorByCpf(String cpf, Collaborator collaborator) {
        return null;
    }

    @Override
    public Collaborator deleteCollaboratorByCpf(String cpf) {
        return null;
    }

    @Override
    public Collaborator createCollaborator(Collaborator collaborator) {
        LocalDate birthdate = collaborator.getBirthdate();
        Period period = Period.between(birthdate, LocalDate.now());

        // Checar se o colaborador está na blacklist

        if (collaboratorRepository.findByCpf(collaborator.getCpf()).isPresent()) {
            throw new RuntimeException();
        }

        if (!sectorRepository.existsById(collaborator.getSector().getId())) {
            throw new RuntimeException();
        }

        // Não é possível cadastrar colaboradores menores de idade ou acima de 60 anos
        if (18 > period.getYears() || period.getYears() > 60) {
            throw new RuntimeException();
        }

        // Mão é possível ter mais do que 30% de colaboradores do sexo masculino
        if (sectorRepository.calculateMalePercentageBySector(collaborator.getSector().getId()) > 30.0) {
            throw new RuntimeException();
        }

        collaborator.setCreatedDate(LocalDateTime.now());
        collaborator.setUpdatedDate(LocalDateTime.now());

        return collaboratorRepository.save(collaborator);
    }
}