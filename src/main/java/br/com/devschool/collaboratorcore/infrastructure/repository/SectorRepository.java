package br.com.devschool.collaboratorcore.infrastructure.repository;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Long> {
}
