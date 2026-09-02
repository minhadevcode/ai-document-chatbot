package dev.aiplayground.aidocumentchatbot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatHistory {
    private String role;
    private String content;
}
