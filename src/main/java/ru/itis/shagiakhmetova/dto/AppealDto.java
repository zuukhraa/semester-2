package ru.itis.shagiakhmetova.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.shagiakhmetova.model.Appeal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppealDto {
    private Integer id;
    private WeatherDto weatherDto;
    private UserDto userDto;

    public static AppealDto fromModel(Appeal appeal) {
        return new AppealDto(appeal.getId(), WeatherDto.fromModel(appeal.getWeather()),
                UserDto.fromModel(appeal.getUser()));
    }
}
