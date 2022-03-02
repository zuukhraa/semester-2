package ru.itis.shagiakhmetova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.shagiakhmetova.helper.WeatherHelper;
import ru.itis.shagiakhmetova.model.Appeal;
import ru.itis.shagiakhmetova.service.AppealService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final AppealService appealService;

    @GetMapping("/weatherSearch")
    public String getWeather(@RequestParam Optional<String> city) throws IOException {
        return WeatherHelper.getCity(city.orElse("Kazan"));
    }

    @GetMapping("/appeals/{user_id}")
    public List<Appeal> getAppealsByUser(@PathVariable Integer user_id) {
        return appealService.getAppealsByUser(user_id);
    }

    @GetMapping("/city/{city}")
    public List<Appeal> getAppealsByCity(@PathVariable String city) {
        return appealService.getAppealsByCity(city);
    }
}
