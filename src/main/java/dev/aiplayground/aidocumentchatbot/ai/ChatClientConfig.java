package dev.aiplayground.aidocumentchatbot.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    //spring이 자동으로 만들어주도록 설정
    @Bean
    public ChatClient ChatClient(ChatClient.Builder builder){
        return builder.build();
    }
}
