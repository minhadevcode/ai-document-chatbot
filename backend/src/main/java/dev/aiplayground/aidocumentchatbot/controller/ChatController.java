package dev.aiplayground.aidocumentchatbot.controller;

import dev.aiplayground.aidocumentchatbot.dto.ChatRequest;
import dev.aiplayground.aidocumentchatbot.dto.ChatResponse;
import dev.aiplayground.aidocumentchatbot.service.AiService;
import dev.aiplayground.aidocumentchatbot.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {

    private final AiService aiService;
    private final DocumentService documentService;
    private final VectorStore vectorStore;

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request){
        if(documentService.getDocumentText() == null){
            return new ChatResponse(request.getQuestion(), "파일이 없습니다. 먼저 PDF 파일을 업로드 해주세요");
        }
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder().query(request.getQuestion()).topK(3).similarityThreshold(0.5).build());

        System.out.println("documents : " +documents);


        if(documents.isEmpty()) {
            return new ChatResponse(request.getQuestion(), "문서에서 관련 내용을 찾을 수 없습니다.");
        }

        String documentText = documents.stream().map(Document::getText).collect(Collectors.joining("\n"));
        String answer = aiService.ask(request.getQuestion(), documentText);

        return new ChatResponse(request.getQuestion(), answer);
    }
}
