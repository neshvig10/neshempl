package com.neshempl.backend.service.impl;

import com.neshempl.backend.service.AIAPIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
public class AIAPIServiceImplement implements AIAPIService {


    @Value("${AI_API_KEY}")
    private static String API_KEY;

    private static final String BASE_URL = "http://api.deepseek.com/v1";


    public String callAIAPI(){
        var body = "{\n" +
                "        \"model\": \"deepseek-chat\",\n" +
                "        \"messages\": [\n" +
                "          {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},\n" +
                "          {\"role\": \"user\", \"content\": \"Hello!\"}\n" +
                "        ],\n" +
                "        \"stream\": false\n" +
                "      }";

        var request = HttpRequest.newBuilder()


        return "";

    }


}
