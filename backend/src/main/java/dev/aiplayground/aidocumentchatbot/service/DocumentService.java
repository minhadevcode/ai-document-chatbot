package dev.aiplayground.aidocumentchatbot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentChunker documentChunker;
    private final EmbeddingService embeddingService;
    private final VectorStore vectorStore;
    private List<String> documentIds = new ArrayList<>();
    private String documentText;
    private List<String> documentChunks;

    public void saveDocument(String text){
        if(!documentIds.isEmpty()){
            vectorStore.delete(documentIds);
            documentIds.clear();
        }
        this.documentText = text;
        this.documentChunks = documentChunker.split(text);
        List<Document> documents = documentChunks.stream().map(Document::new).toList();
        vectorStore.add(documents);
        documentIds = new ArrayList<>(documents.stream().map(Document::getId).toList());
    }

}
