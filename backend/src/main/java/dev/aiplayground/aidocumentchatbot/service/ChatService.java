package dev.aiplayground.aidocumentchatbot.service;

import dev.aiplayground.aidocumentchatbot.dto.ChatRequest;
import dev.aiplayground.aidocumentchatbot.dto.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    public ChatResponse chat(ChatRequest chatRequest){
        return new ChatResponse(
                chatRequest.getQuestion(), "Service 계층으로 처리한 답변입니다."
        );
    }
}
