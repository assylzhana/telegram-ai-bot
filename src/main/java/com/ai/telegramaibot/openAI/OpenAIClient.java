package com.ai.telegramaibot.openAI;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
public class OpenAIClient {
    private final String token;
    private final RestTemplate restTemplate;

    public ChatCompletionObject createChatCompletion(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        httpHeaders.set("Content-type", "application/json");

        String request = """
                {
                    "model": "gpt-3.5-turbo",
                    "messages": [
                      {
                        "role": "system",
                        "content": "You are a helpful assistant."
                      },
                      {
                        "role": "user",
                        "content": "%s"
                      }
                    ]
                  }
                """.formatted(message);

        HttpEntity<String> httpEntity = new HttpEntity<>(request, httpHeaders);

        try {
            ResponseEntity<ChatCompletionObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ChatCompletionObject.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        return new ChatCompletionObject(List.of(new Choice(new Message("system", "Error: "))));
    }
}
