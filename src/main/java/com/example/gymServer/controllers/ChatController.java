package com.example.gymServer.controllers;

import com.example.gymServer.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        if (message.getUserId() == null || message.getUserId().isEmpty()) {
            message.setUserId(UUID.randomUUID().toString());
        }

        // Anonimowi użytkownicy mogą wysyłać wiadomości
        message.setSender("user");
        System.out.println(message);
        return message;
    }

    @MessageMapping("/adminReply")
    @SendTo("/topic/messages")
    public ChatMessage adminReply(ChatMessage message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MANAGER"))) {
            message.setSender(authentication.getName());
            return message;
        } else {
            throw new AccessDeniedException("Tylko administratorzy i menedżerowie mogą odpowiadać");
        }/*
        message.setSender("Admin");
        return message;*/
    }

}
