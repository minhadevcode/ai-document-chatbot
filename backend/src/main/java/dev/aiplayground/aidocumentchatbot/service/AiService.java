package dev.aiplayground.aidocumentchatbot.service;

import dev.aiplayground.aidocumentchatbot.dto.ChatHistory;

import java.util.List;

public interface AiService {
    String ask(String question, String documentText, List<ChatHistory> history);
}
