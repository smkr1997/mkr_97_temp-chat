package com.tmp.chat.controller;

import com.tmp.chat.modle.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public void sendMessage(ChatMessage message) {
        messagingTemplate.convertAndSend("/topic/messages/" + message.getReceiver(), message);
    }

}
