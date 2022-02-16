package ru.itis.shagiakhmetova.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class MainService {
    public String getCity(String city) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + "50da205a9c76cfaf41a554bc57768910");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        StringBuilder result = new StringBuilder();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String input;
            while ((input = reader.readLine()) != null) {
                result.append(input);
            }
        }
        connection.disconnect();
        return result.toString();
    }

    public Map<String, String> parseJson(String json) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        int temp = (int) (jsonNode.get("main").get("temp").asDouble() - 273.15);

        map.put("temp", String.valueOf(temp));
        map.put("humidity", jsonNode.get("main").get("humidity").asText());
        map.put("name", jsonNode.get("name").asText());

        return map;
    }
}
