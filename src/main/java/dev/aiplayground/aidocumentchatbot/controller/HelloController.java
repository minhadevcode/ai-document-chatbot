package dev.aiplayground.aidocumentchatbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello, AI Document Chatbot";
    }
}
