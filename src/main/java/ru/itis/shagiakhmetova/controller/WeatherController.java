package ru.itis.shagiakhmetova.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Returns all weather")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weathers were received",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = WeatherDto.class)
                            )
                    }
            )
    })
    @GetMapping("/allWeather")
    @ResponseBody
    public Iterable<WeatherDto> getAll() {
        return mainService.getAll();
    }

    @Operation(summary = "Returns appeals by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeals were received",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AppealDto.class)
                            )
                    }
            )
    })
    @GetMapping("/appeals/{user_id}")
    @ResponseBody
    @Loggable
    public List<AppealDto> getAppealsByUser(@PathVariable Integer user_id) {
        return appealService.getAppealsByUser(user_id);
    }

    @Operation(summary = "Returns appeals by city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeals were received",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AppealDto.class)
                            )
                    }
            )
    })
    @GetMapping("/city/{city}")
    @ResponseBody
    public List<AppealDto> getAppealsByCity(@PathVariable String city) {
        return appealService.getAppealsByCity(city);
    }

    @Operation(summary = "Returns weather by city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather was received",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/weather")
    @ResponseBody
    public Weather getWeather(@RequestParam Optional<String> city, Authentication authentication) throws IOException {
        return mainService.getWeather(city, authentication);
    }

    @Operation(summary = "Returns all weather information by city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather was received",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = WeatherDto.class)
                            )
                    }
            )
    })
    @GetMapping("/weatherByCity/{city}")
    @ResponseBody
    public List<WeatherDto> getWeatherByCity(@PathVariable String city) {
        return mainService.getWeatherByCity(city);
    }

    @Operation(summary = "Search weather by city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather was received",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/weatherSearch")
    @ResponseBody
    public String getWeather(@RequestParam Optional<String> city) throws IOException {
        return WeatherHelper.getCity(city.orElse("Kazan"));
    }
}


