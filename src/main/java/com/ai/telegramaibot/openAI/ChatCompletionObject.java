package com.ai.telegramaibot.openAI;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ChatCompletionObject {

    private final List<Choice> choices;
}
