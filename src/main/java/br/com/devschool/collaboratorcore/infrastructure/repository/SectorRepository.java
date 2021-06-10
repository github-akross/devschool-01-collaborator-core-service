package br.com.devschool.collaboratorcore.infrastructure.repository;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAll();
    Sector findByName(String name);
    Boolean existsByName(String name);

    @Query(value = "SELECT pct " +
            "from (SELECT s.id, " +
            "             (count(c.id) + 1) * 100.0 / (SELECT count(c2.id) + 1 " +
            "                                    from sector s " +
            "                                             inner join collaborator c2 on s.id = c2.sector_id " +
            "                                    where s.id = ?0) as pct " +
            "      from sector s " +
            "               inner join collaborator c on s.id = c.sector_id " +
            "      where c.gender = 'm' and s.id = ?0 " +
            "      group by s.id) as sub;", nativeQuery = true)
    float calculateMalePercentageBySector(Long id);
}
