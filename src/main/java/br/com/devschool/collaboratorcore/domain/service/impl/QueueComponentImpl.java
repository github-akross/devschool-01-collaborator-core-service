package br.com.devschool.collaboratorcore.domain.service.impl;

import br.com.devschool.collaboratorcore.domain.service.QueueComponent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

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
