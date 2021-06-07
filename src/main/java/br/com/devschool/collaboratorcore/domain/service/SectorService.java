package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectorService {

    List<Sector> getAllSector();

    ResponseEntity<Sector> getSectorByName(String name);

    ResponseEntity<Sector> deleteSectorById(Long id);

    ResponseEntity<Sector> saveSector(Sector sector);

    ResponseEntity<Sector> updateSectorById(Long id);
 }
