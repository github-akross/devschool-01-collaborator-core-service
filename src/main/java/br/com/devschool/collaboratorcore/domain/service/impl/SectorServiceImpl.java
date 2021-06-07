package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.SectorService;
import br.com.devschool.collaboratorcore.infrastructure.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class SectorServiceImpl implements SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public List<Sector> getAllSector() {
        return sectorRepository.findAll();
    }

    @Override
    public ResponseEntity<Sector> getSectorByName(String name) {
        return sectorRepository.findByName(name);
    }

    @Override
    public ResponseEntity<Sector> deleteSectorById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Sector> saveSector(Sector sector) {
        return null;
    }

    @Override
    public ResponseEntity<Sector> updateSectorById(Long id) {
        return null;
    }
}
