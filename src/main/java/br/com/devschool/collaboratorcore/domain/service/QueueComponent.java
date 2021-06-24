package br.com.devschool.collaboratorcore.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface QueueComponent {
    <T> void sendMessage(T message , String queueName) throws JsonProcessingException;
}
