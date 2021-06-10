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

    //  get:  listagem de todos os setores
    @GetMapping("/sector")
    public ResponseEntity<List<Sector>> getAllSector(){
        return ResponseEntity.ok(sectorService.getAllSector());
    }

    //  get: listar 1 sector pelo nome
    @GetMapping("/sector/{name}")
    public ResponseEntity<Sector> getSectorByName(@PathVariable String name){
        return ResponseEntity.ok(sectorService.getSectorByName(name));
    }

    //  Criar sector
    @PostMapping("/sector")
    public ResponseEntity<Sector> saveSector(@RequestBody Sector sector){
        return ResponseEntity.ok(sectorService.saveSector(sector));
    }

    //  Atualizar sector pelo id
    @PutMapping("/sector/{id}")
    public  ResponseEntity<Sector> updateSectorById(@PathVariable Long id, @RequestBody Sector sector){
        return ResponseEntity.ok(sectorService.updateSectorById(id, sector));
    }

    //  Deletar Sector pelo id
    @DeleteMapping("/sector/{id}")
    public ResponseEntity deleteSectorById(@PathVariable Long id){
        sectorService.deleteSectorById(id);
        return ResponseEntity.ok().build();
    }
}
