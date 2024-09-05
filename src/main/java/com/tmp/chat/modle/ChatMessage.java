package com.tmp.chat.modle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage implements Serializable {

    private String sender;
    private String receiver;
    private String content;

}