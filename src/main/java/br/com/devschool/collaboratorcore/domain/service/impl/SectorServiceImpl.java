package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.SectorService;
import br.com.devschool.collaboratorcore.infrastructure.exception.SectorAlreadyExistsException;
import br.com.devschool.collaboratorcore.infrastructure.exception.SectorNotFoundException;
import br.com.devschool.collaboratorcore.infrastructure.repository.SectorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;

    // ordens de serviço de Sector

    @Override
    public List<Sector> getAllSector() {
        return sectorRepository.findAll();
    }

    // Dispara Setor nao existe
    @Override
    public Sector getSectorByName(String name) {
        return sectorRepository.findByName(name).orElseThrow(() -> new SectorNotFoundException(name));
    }

    @Override
    public Sector saveSector(Sector sector) {
        // Não é possivel cadastar um sector que já existe
        if (sectorRepository.existsByName(sector.getName())) {
            throw new SectorAlreadyExistsException(sector.getName());
        }

        sector.setCreatedDate(LocalDateTime.now());
        sector.setUpdatedDate(LocalDateTime.now());

        return sectorRepository.save(sector);
    }

    @Override
    public Sector updateSectorById(Long id, Sector sector) {
        // Não é possveil atualizar um sector que não existe
        Optional<Sector> sectorOptional = sectorRepository.findById(id);

        if (!sectorOptional.isPresent()) {
            throw new SectorNotFoundException(id.toString());
        }

        Sector sectorExistent = sectorOptional.get();

        // Atualiza o sector
        return sectorRepository.save(Sector.builder()
                .id(sectorExistent.getId())
                .name(sector.getName())
                .description(sector.getDescription())
                .createdDate(sectorExistent.getCreatedDate())
                .updatedDate(LocalDateTime.now())
                .build());
    }

    @Override
    public void deleteSectorById(Long id) {
        sectorRepository.deleteById(id);
    }
}
