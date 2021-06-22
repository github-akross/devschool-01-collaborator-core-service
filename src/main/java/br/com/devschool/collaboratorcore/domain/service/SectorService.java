package br.com.devschool.collaboratorcore.domain.service;

import br.com.devschool.collaboratorcore.domain.model.Sector;

import java.util.List;

public interface SectorService {

    // Assinatura dos serviços de Sector

    List<Sector> getAllSector();

    Sector getSectorByName(String name);

    Sector saveSector(Sector sector);

    Sector updateSectorById(Long id, Sector sector);

    void deleteSectorById(Long id);
 }
