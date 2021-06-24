package br.com.devschool.collaboratorcore.application.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.SectorService;
import br.com.devschool.collaboratorcore.infrastructure.exception.SectorAlreadyExistsException;
import br.com.devschool.collaboratorcore.infrastructure.exception.SectorNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class SectorControllerTest {
    @InjectMocks
    private SectorController controller;

    @Mock
    private SectorService sectorService;

    private final String SECTOR_NAME = "SECTOR_NAME";

    // Define um mock de Sector
    private Sector mockSector(){
        return  Sector.builder()
                .id(1L)
                .name("Teste")
                .description("Setor Teste")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    // Teste de GetSectorByName
    @Test
    public void givenSectorNameReturnSector() {
        Sector sector = mockSector();
        doReturn(sector).when(sectorService).getSectorByName(SECTOR_NAME);

        ResponseEntity<Sector> result = controller.getSectorByName(SECTOR_NAME);

        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(sector, result.getBody());
    }

    @Test(expected = SectorNotFoundException.class)
    public void givenSectorNameThatNotExistAssertException() {
        doThrow(SectorNotFoundException.class).when(sectorService).getSectorByName(SECTOR_NAME);

        ResponseEntity<Sector> result = controller.getSectorByName(SECTOR_NAME);

        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    // Teste de CreateSector
    @Test
    public void givenNewSectorReturnSector() {

    }

    @Test(expected = SectorAlreadyExistsException.class)
    public void givenSectorThatAlreadyExistsAssertException() {
        Sector sector = mockSector();
        doThrow(SectorAlreadyExistsException.class).when(sectorService).saveSector(sector);

        ResponseEntity<Sector> result = controller.saveSector(sector);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    // Teste de updateSector

}