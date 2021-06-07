package br.com.devschool.collaboratorcore.infrastructure.repository;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAll();
    ResponseEntity<Sector> findByName(String name);
}
