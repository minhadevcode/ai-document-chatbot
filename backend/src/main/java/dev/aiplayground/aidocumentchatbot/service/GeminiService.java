package dev.aiplayground.aidocumentchatbot.service;

import dev.aiplayground.aidocumentchatbot.dto.ChatHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiService implements AiService {

    private final ChatClient chatClient;

    @Override
    public String ask(
            String question,
            String documentText,
            List<ChatHistory> history
    ) {

        String conversation = "";

        if (history != null && !history.isEmpty()) {
            conversation = history.stream()
                    .map(chat -> chat.getRole() + ": " + chat.getContent())
                    .reduce("", (a, b) -> a + b + "\n");
        }

        String prompt = """
            너는 문서에 있는 내용만을 근거로 답변하는 문서 분석 AI야.

            반드시 다음 규칙을 지켜:
            1. 아래 [문서]에 있는 내용만 사용해서 답변해.
            2. 네가 기존에 알고 있는 지식이나 일반적인 상식을 사용하지 마.
            3. 질문과 관련된 내용이 문서에 있으면 그 내용을 근거로 답변해.
            4. 질문과 관련된 내용이 문서에 전혀 없을 때만 "문서에서 확인할 수 없습니다."라고만 답변해.
            5. 문서의 내용을 임의로 수정하거나 추측하지 마.
            6. [이전 대화]는 현재 질문의 맥락을 이해하기 위해서만 사용해.

            [문서]
            %s

            [이전 대화]
            %s

            [현재 질문]
            %s
        """.formatted(documentText, conversation, question);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}