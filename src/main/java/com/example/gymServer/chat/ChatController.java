package com.example.gymServer.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/api/v1/sendMessage")
    @SendTo("/api/v1/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        // Anonimowi użytkownicy mogą wysyłać wiadomości
        message.setSender("Anonymous");
        return message;
    }

    @MessageMapping("/api/v1/adminReply")
    @SendTo("/api/v1/topic/messages")
    public ChatMessage adminReply(ChatMessage message) {
        // Tylko administratorzy i menedżerowie mogą odpowiedzieć
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MANAGER"))) {
            message.setSender(authentication.getName());
            return message;
        } else {
            throw new AccessDeniedException("Tylko administratorzy i menedżerowie mogą odpowiadać");
        }
    }
}
