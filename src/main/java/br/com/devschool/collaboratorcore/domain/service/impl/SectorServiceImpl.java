package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.SectorService;
import br.com.devschool.collaboratorcore.infrastructure.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;

    @Override
    public List<Sector> getAllSector() {
        return sectorRepository.findAll();
    }

    @Override
    public Sector getSectorByName(String name) {
        return sectorRepository.findByName(name);
    }

    @Override
    public void deleteSectorById(Long id) {
    }

    @Override
    public Sector saveSector(Sector sector) {
        return null;
    }

    @Override
    public Sector updateSectorById(Long id) {
        return null;
    }
}
