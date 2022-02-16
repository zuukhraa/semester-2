package ru.itis.shagiakhmetova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shagiakhmetova.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
