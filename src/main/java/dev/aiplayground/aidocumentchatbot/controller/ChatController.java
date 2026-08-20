package dev.aiplayground.aidocumentchatbot.controller;

import dev.aiplayground.aidocumentchatbot.dto.ChatRequest;
import dev.aiplayground.aidocumentchatbot.dto.ChatResponse;
import dev.aiplayground.aidocumentchatbot.service.AiService;
import dev.aiplayground.aidocumentchatbot.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {

    private final AiService aiService;
    private final DocumentService documentService;

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request){
//        todo 문서가 있는지 체크 - null 체크 필요

        List<String> chunks = documentService.search(request.getQuestion());

        System.out.println("검색된 chunk 수 : " + chunks.size());
        String documentText = String.join("\n", chunks);
        String answer = aiService.ask(request.getQuestion(),  documentText);

        return new ChatResponse(request.getQuestion(), answer);
    }
}
