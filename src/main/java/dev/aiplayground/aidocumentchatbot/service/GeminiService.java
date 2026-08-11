package dev.aiplayground.aidocumentchatbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService  implements AiService {
    private final ChatClient chatClient;

    @Override
    public String ask(String question) {
        return chatClient.prompt().user(question).call().content();
    }
}
