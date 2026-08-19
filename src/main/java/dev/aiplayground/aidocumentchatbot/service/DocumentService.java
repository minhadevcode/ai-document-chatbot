package dev.aiplayground.aidocumentchatbot.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class DocumentService {
    private String documentText;

    public void saveDocument(String text){
        this.documentText = text;
    }
}
