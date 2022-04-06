package ru.itis.shagiakhmetova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.shagiakhmetova.aspect.Loggable;
import ru.itis.shagiakhmetova.dto.AppealDto;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.helper.WeatherHelper;
import ru.itis.shagiakhmetova.model.Weather;
import ru.itis.shagiakhmetova.service.AppealService;
import ru.itis.shagiakhmetova.service.MainService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class WeatherController {

    @Autowired
    private final MainService mainService;

    @Autowired
    private final AppealService appealService;

    @GetMapping("/allWeather")
    @ResponseBody
    public Iterable<WeatherDto> getAll() {
        return mainService.getAll();
    }

    @GetMapping("/weatherSearch")
    @ResponseBody
    public String getWeather(@RequestParam Optional<String> city) throws IOException {
        return WeatherHelper.getCity(city.orElse("Kazan"));
    }

    @GetMapping("/appeals/{user_id}")
    @ResponseBody
    @Loggable
    public List<AppealDto> getAppealsByUser(@PathVariable Integer user_id) {
        return appealService.getAppealsByUser(user_id);
    }

    @GetMapping("/city/{city}")
    @ResponseBody
    public List<AppealDto> getAppealsByCity(@PathVariable String city) {
        return appealService.getAppealsByCity(city);
    }

    @GetMapping("/weather")
    @ResponseBody
    public Weather getWeather(@RequestParam Optional<String> city, Authentication authentication) throws IOException {
        return mainService.getWeather(city, authentication);
    }

    @GetMapping("/weatherByCity/{city}")
    @ResponseBody
    public List<WeatherDto> getWeatherByCity(@PathVariable String city) {
        return mainService.getWeatherByCity(city);
    }
}


