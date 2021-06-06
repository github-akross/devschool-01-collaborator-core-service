package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectorService {

    ResponseEntity<List<Sector>> getAllSector();

    ResponseEntity<Sector> getSectorbyName(String name);

    ResponseEntity<Sector> deleteSectorbyId(Long id);

    ResponseEntity<Sector> saveSector(Sector sector);

    ResponseEntity<Sector> updateSectorById(Long id);
 }
