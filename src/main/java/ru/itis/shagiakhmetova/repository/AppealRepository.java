package ru.itis.shagiakhmetova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shagiakhmetova.model.Appeal;

import java.util.List;

public interface AppealRepository extends JpaRepository<Appeal, Integer> {
    List<Appeal> getAppealsByUserId(Integer id);
    List<Appeal> getAppealsByWeatherCity(String city);
}
