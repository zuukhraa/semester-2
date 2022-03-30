package ru.itis.shagiakhmetova.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.shagiakhmetova.model.User;
import ru.itis.shagiakhmetova.model.Weather;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherDto {
    private Integer id;
    private String email;
    private String temp;
    private String humidity;
    private String city;

    public static WeatherDto from(Weather weather) {
        return WeatherDto.builder()
                .id(weather.getId())
                .email(weather.getEmail())
                .temp(weather.getTemp())
                .humidity(weather.getHumidity())
                .city(weather.getCity())
                .build();
    }

    public static List<WeatherDto> from(List<Weather> weathers) {
        return weathers.stream().map(WeatherDto::from).collect(Collectors.toList());
    }

    public static WeatherDto fromModel(Weather weather) {
        return new WeatherDto(weather.getId(), weather.getEmail(), weather.getTemp(), weather.getHumidity(), weather.getCity());
    }

}
