package com.ai.telegramaibot.openAI;


import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice (
        @JsonProperty("message")  Message message) {}