package dev.aiplayground.aidocumentchatbot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@NoArgsConstructor 실무에서는 이것도 씀 Jackson(JSON 변환)
public class ChatRequest {
    @NotBlank(message= "질문을 입력해주세요.")
    private String question;
    private List<ChatHistory> history;

}
