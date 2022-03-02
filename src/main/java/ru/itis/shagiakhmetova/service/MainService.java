package ru.itis.shagiakhmetova.service;

import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.model.Weather;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MainService {
    Iterable<WeatherDto> getAll();
    Weather getWeather(Optional<String> city, @RequestParam String email) throws IOException;
    List<WeatherDto> getWeatherByCity (String city);
}
