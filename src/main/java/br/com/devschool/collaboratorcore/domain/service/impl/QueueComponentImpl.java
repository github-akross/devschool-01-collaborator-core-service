package br.com.devschool.collaboratorcore.domain.service.impl;

import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import br.com.devschool.collaboratorcore.domain.service.QueueComponent;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
@RequiredArgsConstructor
@Slf4j
public class QueueComponentImpl implements QueueComponent {

    private final ProducerTemplate producerTemplate;

    private final ObjectMapper objectMapper;


    @Override
    public <T> void sendMessage(T message, String queueName) {
        try{
            producerTemplate.sendBody(queueName, objectMapper.writeValueAsString(message));
        } catch (Exception ex){
            log.error("The message {} wasn't sent to queue {}", message, queueName);
        }
    }
}
