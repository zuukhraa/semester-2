package ru.itis.shagiakhmetova.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.shagiakhmetova.service.MainService;

import java.io.IOException;
import java.util.Optional;

@RestController
public class MainController {
    private final MainService mainService = new MainService();

    @GetMapping("/weatherSearch")
    public String getWeather(@RequestParam Optional<String> city) throws IOException {
        return mainService.getCity(city.orElse("Kazan"));
    }
}
