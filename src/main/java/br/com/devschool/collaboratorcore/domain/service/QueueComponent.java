package br.com.devschool.collaboratorcore.domain.service;

import org.springframework.stereotype.Component;

@Component
public interface QueueComponent {
    <T> void sendMessage(T message , String queueName);
}
