package ru.itis.shagiakhmetova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.shagiakhmetova.model.Appeal;
import ru.itis.shagiakhmetova.repository.AppealRepository;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AppealServiceImpl implements AppealService {

    private final AppealRepository appealRepository;
    @Override
    public List<Appeal> getAppealsByUser(Integer id) {
        return appealRepository.getAppealsByUserId(id);
    }

    @Override
    public List<Appeal> getAppealsByCity(String city) {
        return appealRepository.getAppealsByWeatherCity(city);
    }
}
