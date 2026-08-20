package dev.aiplayground.aidocumentchatbot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentChunker documentChunker;
    private String documentText;
    private List<String> documentChunks;

    public void saveDocument(String text){
        this.documentChunks = documentChunker.split(text);

        System.out.println("====CHUNKS====");
        System.out.println("documentChunks.size: " + documentChunks.size());
        for(int i = 0;i<documentChunks.size();i++){
            System.out.println("Chunk : " + (i+1) + ":" + documentChunks.get(i).length() + "자");
        }
        System.out.println("=============");
    }

    public List<String> search(String question){
        String[] keywords = question.split("\\s+");

        return documentChunks.stream().filter(chunk -> {
            for(String keyword : keywords){
                if(keyword.length() >= 2 && chunk.contains(keyword)){
                    return true;
                }
            }
            return false;
        }).toList();
    }
}
