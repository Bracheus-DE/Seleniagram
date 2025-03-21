package eu.hobbydev.bracheus.manager;


import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import eu.hobbydev.bracheus.Seleniagram;

import java.util.List;

/**
 * Copyright (C) 2025 Bracheus
 * <p>
 * All rights reserved. Unauthorized copying, distribution, or modification
 * of this code without express permission from Bracheus is
 * strictly prohibited.
 * <p>
 * This project is the result of personal development and innovation by Bracheus.
 * By using or modifying this code, you acknowledge the ownership of Bracheus
 * and agree to comply with the terms outlined in the project’s
 * licensing agreement.
 * <p>
 * Created by: Bracheus (GitHub: https://github.com/Bracheus-DE)
 * <p>
 * For inquiries, collaborations, or licensing information, contact:
 * bracheus@hobbydev.eu
 */

/**
 * The `OpenAIManager` class is responsible for interacting with the OpenAI API to generate responses
 * for messages or comments. It uses an OpenAI client to send input to the GPT model and get generated responses.
 * This class is designed to automatically generate answers to a list of messages, with specific rules for the response.
 * <p>
 * The class is initialized with an API key to authenticate with the OpenAI API. It contains methods for constructing
 * prompts from provided messages and receiving responses generated by the OpenAI language model.
 * <p>
 * The main functionality of this class is to take a list of messages (such as Instagram messages or comments)
 * and return a generated answer. The answer is formulated based on the context of the given messages, following specific instructions.
 */
public class OpenAIManager {

    private final OpenAIClient openAIClient;

    /**
     * Constructs an `OpenAIManager` instance with the specified API key.
     *
     * @param apiKey the API key used to authenticate with the OpenAI API.
     * @throws IllegalArgumentException if the API key is null or invalid.
     */
    public OpenAIManager(String apiKey) {
        this.openAIClient = new OpenAIOkHttpClient.Builder().apiKey(apiKey).build();
    }

    /**
     * Generates a response for a list of given messages.
     * <p>
     * This method formats the input messages into a string, following a specific structure that instructs the OpenAI model
     * to generate a response without adding any extra text. The model will also follow the instruction that if asked for a name,
     * the response should include the username".
     *
     * @param nachrichten a list of messages (e.g., comments or text) that need responses.
     * @return a string containing the response generated by the OpenAI model based on the input messages.
     */
    public String answerMessages(List<String> nachrichten) {
        StringBuilder stringBuilder = new StringBuilder(1000);

        stringBuilder.append("Write an answer for this given messages, please dont write anything else, if you get asked for a name, ur name is " + Seleniagram.configurationHolder.getUsername() + ":");
        for (String nachricht : nachrichten) {
            stringBuilder.append("\"");
            stringBuilder.append(nachricht);
            stringBuilder.append("\"");
        }
        ResponseCreateParams params = ResponseCreateParams.builder()
                .input(stringBuilder.toString())
                .model(ChatModel.GPT_4O_MINI_2024_07_18)
                .build();
        Response response = getOpenAIClient().responses().create(params);
        return response.output().get(0).asMessage().content().get(0).asOutputText().text();
    }

    /**
     * Gets the OpenAI client instance used for making requests to the OpenAI API.
     *
     * @return the `OpenAIClient` instance used for interacting with the OpenAI API.
     */
    public OpenAIClient getOpenAIClient() {
        return openAIClient;
    }
}
