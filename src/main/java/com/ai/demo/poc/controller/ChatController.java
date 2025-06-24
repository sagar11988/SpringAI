package com.ai.demo.poc.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private ChatClient chatClient;

    public ChatController(ChatClient.Builder builder,
                          VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore,
                        SearchRequest.defaults()))
                .build();
    }


    @GetMapping("/askQuestion")
    public String askQuestion(@RequestParam(value = "message",
            defaultValue = "tell me joke")
                              String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }
}
