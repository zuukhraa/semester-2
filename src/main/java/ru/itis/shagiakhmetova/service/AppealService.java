package ru.itis.shagiakhmetova.service;

import ru.itis.shagiakhmetova.dto.AppealDto;
import ru.itis.shagiakhmetova.model.Appeal;

import java.util.List;

public interface AppealService {
    List<AppealDto> getAppealsByUser(Integer id);
    List<AppealDto> getAppealsByCity(String city);
}
