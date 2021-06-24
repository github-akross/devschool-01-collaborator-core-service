package br.com.devschool.collaboratorcore.infrastructure.queue;

import br.com.devschool.collaboratorcore.domain.model.Collaborator;
import br.com.devschool.collaboratorcore.domain.processor.CollaboratorProcesssor;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

@CommandNaming
@RequiredArgsConstructor
public class CollaboratorRouteBulder extends RouteBuilder {
    private  final CollaboratorProcesssor collaboratorProcessor;


    @Override
    public void configure() throws Exception {
        from("{{application.queues.consumer.collaborator-url}}")
          .unmarshal()
          .json(JsonLibrary.Jackson, Collaborator.class)
          .process(collaboratorProcessor);

    }
}
