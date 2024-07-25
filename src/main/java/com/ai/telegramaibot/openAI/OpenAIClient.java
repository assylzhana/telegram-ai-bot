package com.ai.telegramaibot.openAI;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class OpenAIClient {
    private final String token;
    private final RestTemplate restTemplate;

    public ChatCompletionObject createChatCompletion(String message){
        String url = "hhtps://api.openai.com/v1/chat/completions";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer "+token);
        httpHeaders.set("Content-type", "application/json");

        String request = """
                {
                    "model": "gpt-4o",
                    "messages": [
                      {
                        "role": "system",
                        "content": "You are a helpful assistant."
                      },
                      {
                        "role": "user",
                        "content": "%s!"
                      }
                    ]
                  }'
                                
                """.formatted(message);
        HttpEntity<String> httpEntity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<ChatCompletionObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ChatCompletionObject.class);
        return responseEntity.getBody();
    }

}
