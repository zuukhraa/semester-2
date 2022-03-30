package ru.itis.shagiakhmetova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.shagiakhmetova.dto.AppealDto;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.model.Appeal;
import ru.itis.shagiakhmetova.repository.AppealRepository;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppealServiceImpl implements AppealService {

    private final AppealRepository appealRepository;
    @Override
    public List<AppealDto> getAppealsByUser(Integer id) {
        return appealRepository.getAppealsByUserId(id).stream().map(AppealDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<AppealDto> getAppealsByCity(String city) {
        return appealRepository.getAppealsByWeatherCity(city).stream().map(AppealDto::fromModel).collect(Collectors.toList());
    }
}
