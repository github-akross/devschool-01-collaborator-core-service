package br.com.devschool.collaboratorcore.application.controller;

import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.SectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SectorController {
    private  final SectorService sectorService ;

    //get:  listagem de todos os setores
    @GetMapping("/sector")
    public ResponseEntity<List<Sector>> getAllSector(){

        return sectorService.getAllSector() ;
    }

    //get: listar 1 sector pelo nome
    @GetMapping("/sector/{name}")
    public ResponseEntity<Sector> getSectorbyName(@PathVariable String name){
        return sectorService.getSectorbyName(name) ;
    }

    //deletar Sector pelo id
    @DeleteMapping("/sector/{id}")
    public ResponseEntity<Sector> deleteSectorbyId(@PathVariable Long id){
        return sectorService.deleteSectorbyId(id) ;
    }

    //Atualizar sector pelo id
    @PatchMapping("/sector/{id}")
    public  ResponseEntity<Sector> updateSectorById(@RequestBody Long id){
        return sectorService.updateSectorById(id) ;
    }

    // Criar sector
    @PostMapping("/sector")
    public ResponseEntity<Sector> saveSector(@RequestBody Sector sector){
        return sectorService.saveSector(sector);
    }

}
