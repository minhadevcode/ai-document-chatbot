package dev.aiplayground.aidocumentchatbot.dto;

import lombok.Getter;

@Getter
public class ChatResponse {

    private String question;
    private String answer;

    public ChatResponse(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

}
