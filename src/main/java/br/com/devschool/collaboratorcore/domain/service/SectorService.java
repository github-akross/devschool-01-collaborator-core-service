package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectorService {

    List<Sector> getAllSector();

    Sector getSectorByName(String name);

    void deleteSectorById(Long id);

    Sector saveSector(Sector sector);

    Sector updateSectorById(Long id);
 }
