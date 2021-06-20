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

    @Override
    public List<Sector> getAllSector() {
        return sectorRepository.findAll();
    }


    //Setor nao existe
    @Override
    public Sector getSectorByName(String name) {
        return sectorRepository.findByName(name).orElseThrow(() -> new SectorNotFoundException(name));
    }

    @Override
    public Sector saveSector(Sector sector) {
        if (sectorRepository.existsByName(sector.getName())) {
            throw new SectorAlreadyExistsException(sector.getName());
        }

        sector.setCreatedDate(LocalDateTime.now());
        sector.setUpdatedDate(LocalDateTime.now());

        return sectorRepository.save(sector);
    }

    @Override
    public Sector updateSectorById(Long id, Sector sector) {
        Optional<Sector> sectorOptional = sectorRepository.findById(id);

        if (!sectorOptional.isPresent()) {
            throw new RuntimeException();
        }
        Sector sectorExistent = sectorOptional.get();

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
