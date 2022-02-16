package ru.itis.shagiakhmetova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.shagiakhmetova.dto.UserDto;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.model.Weather;
import ru.itis.shagiakhmetova.repository.UserRepository;
import ru.itis.shagiakhmetova.repository.WeatherRepository;
import ru.itis.shagiakhmetova.service.MainService;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WeatherController {
    private final MainService mainService = new MainService();
    private final WeatherRepository weatherRepository;
    private final UserRepository userRepository;

    @Autowired
    public WeatherController(UserRepository userRepository, WeatherRepository weatherRepository) {
        this.userRepository = userRepository;
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/allWeather")
    public Iterable<WeatherDto> getAll() {
        return weatherRepository.findAll().stream().map(WeatherDto::fromModel).collect(Collectors.toList());
    }

    @GetMapping("/weather")
    public Weather getWeather(@RequestParam Optional<String> city, @RequestParam String email) throws IOException {
        UserDto user = userRepository.findByEmail(email);
        if (user != null) {
            String result = mainService.getCity(city.orElse("Kazan"));
            Map<String, String> data = mainService.parseJson(result);
            return weatherRepository.save(
                    new Weather(data.get("temp"), data.get("humidity"), data.get("name"), email));
        } else {
            return null;
        }
    }
}


