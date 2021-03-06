package ru.itis.shagiakhmetova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.helper.WeatherHelper;
import ru.itis.shagiakhmetova.model.User;
import ru.itis.shagiakhmetova.model.Weather;
import ru.itis.shagiakhmetova.repository.UserRepository;
import ru.itis.shagiakhmetova.repository.WeatherRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static ru.itis.shagiakhmetova.dto.WeatherDto.from;

@RequiredArgsConstructor
@Service
public class MainServiceImpl implements MainService {

    private final WeatherRepository weatherRepository;

    private final UserRepository userRepository;

    @Override
    public List<WeatherDto> getAll() {
        return weatherRepository.findAll().stream().map(WeatherDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public Weather getWeather(Optional<String> city, Authentication authentication) throws IOException {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String result = WeatherHelper.getCity(city.orElse("Kazan"));
            Map<String, String> data = WeatherHelper.parseJson(result);
            return weatherRepository.save(
                    new Weather(email, data.get("temp"), data.get("humidity"), data.get("name")));
        } else {
            return null;
        }
    }

    @Override
    public List<WeatherDto> getWeatherByCity(String city) {
        return weatherRepository.getWeatherByCity(city).stream().map(WeatherDto::fromModel).collect(Collectors.toList());
    }
}


