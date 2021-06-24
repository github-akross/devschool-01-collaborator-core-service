package br.com.devschool.collaboratorcore.domain.processor;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.model.Sector;
import br.com.devschool.collaboratorcore.domain.service.CollaboratorService;
import br.com.devschool.collaboratorcore.domain.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollaboratorProcesssor implements Processor {

    private final  CollaboratorService collaboratorService;

    private  final SectorService sectorService;

    @Override
    public void process(Exchange exchange) throws Exception {
      Collaborator collaborator =  exchange.getIn().getBody(Collaborator.class);
        ResponseEntity<List<Collaborator>> collaboratorcpf = collaboratorService.getCollaboratorByCpf(collaborator.getCpf());

        if (collaboratorcpf.getStatusCodeValue() == HttpStatus.OK.value()){
            Optional<Sector> sectorOptional = collaboratorcpf.getBody().stream().findFirst();

               if(sectorOptional.isPresent()){
                 Sector sector = sectorOptional.get();
                 collaboratorService.sendCollaboratorToQueue(sector);
               }
        }
    }
}
