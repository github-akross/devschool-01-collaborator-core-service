package br.com.devschool.collaboratorcore.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

@Component
public interface QueueComponent {
    <T> void sendMessage(T message , String queueName);
}
