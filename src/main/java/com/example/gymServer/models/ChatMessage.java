package com.example.gymServer.models;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
    private String userId;
    private MessageType type;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}
