package ru.itis.shagiakhmetova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.model.Weather;
import ru.itis.shagiakhmetova.service.MainService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class WeatherController {

    private final MainService mainService;

    @GetMapping("/allWeather")
    public Iterable<WeatherDto> getAll() {
        return mainService.getAll();
    }

    @GetMapping("/weather")
    public Weather getWeather(@RequestParam Optional<String> city, @RequestParam String email) throws IOException {
        return mainService.getWeather(city, email);
    }

    @GetMapping("/weatherByCity/{city}")
    public List<WeatherDto> getWeatherByCity(@PathVariable String city) {
        return mainService.getWeatherByCity(city);
    }
}


