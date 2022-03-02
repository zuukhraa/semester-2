package ru.itis.shagiakhmetova.service;

import ru.itis.shagiakhmetova.model.Appeal;

import java.util.List;

public interface AppealService {
    List<Appeal> getAppealsByUser(Integer id);
    List<Appeal> getAppealsByCity(String city);
}
