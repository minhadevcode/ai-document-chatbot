package dev.aiplayground.aidocumentchatbot.controller;

import dev.aiplayground.aidocumentchatbot.dto.ChatRequest;
import dev.aiplayground.aidocumentchatbot.dto.ChatResponse;
import dev.aiplayground.aidocumentchatbot.service.AiService;
import dev.aiplayground.aidocumentchatbot.service.ChatService;
import dev.aiplayground.aidocumentchatbot.service.DocumentService;
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

    private final AiService aiService;
    private final DocumentService documentService;

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request){
        String documentText = documentService.getDocumentText();
        String answer = aiService.ask(request.getQuestion(), documentText);
        return new ChatResponse(request.getQuestion(), answer);
    }
}
