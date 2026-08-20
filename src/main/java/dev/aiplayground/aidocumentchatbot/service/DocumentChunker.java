package dev.aiplayground.aidocumentchatbot.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentChunker {
    private static final int CHUNK_SIZE = 300;

    public List<String> split(String text){
        List<String> chunks = new ArrayList<>();
        for(int start = 0; start < text.length(); start+=CHUNK_SIZE){
            int end = Math.min(start+CHUNK_SIZE, text.length());

            chunks.add(text.substring(start, end));
        }
        return chunks;
    }
}
