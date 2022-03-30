package ru.itis.shagiakhmetova.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.shagiakhmetova.model.Weather;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class WeatherRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void testGetWeatherByCity() {
        Weather weather = new Weather();
        weather.setEmail("test@mail.ru");
        weather.setCity("Kazan");
        weather.setTemp("-6");
        weather.setHumidity("75");
        testEntityManager.persistAndFlush(weather);

        List<Weather> result = weatherRepository.getWeatherByCity("Kazan");
        Assert.assertNotNull(result);
    }
}
