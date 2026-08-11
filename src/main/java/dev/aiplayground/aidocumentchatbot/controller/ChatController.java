package dev.aiplayground.aidocumentchatbot.controller;

import dev.aiplayground.aidocumentchatbot.dto.ChatRequest;
import dev.aiplayground.aidocumentchatbot.dto.ChatResponse;
import dev.aiplayground.aidocumentchatbot.service.AiService;
import dev.aiplayground.aidocumentchatbot.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;
    private final AiService aiService;

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request){

        String answer = aiService.ask(request.getQuestion());

        return new ChatResponse(request.getQuestion(), answer);
    }
}
