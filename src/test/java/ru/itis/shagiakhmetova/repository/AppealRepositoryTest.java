package ru.itis.shagiakhmetova.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.shagiakhmetova.model.Appeal;
import ru.itis.shagiakhmetova.model.User;
import ru.itis.shagiakhmetova.model.Weather;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AppealRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AppealRepository appealRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void testGetAppealsByUserId() {
        User user = new User();
        user.setEmail("m@mail.ru");
        user.setName("Ivan");
        user.setPassword("password");
        user.setVerificationCode("apple");
        userRepository.save(user);

        Appeal appeal = new Appeal();
        appeal.setUser(user);
        testEntityManager.persistAndFlush(appeal);

        List<Appeal> result = appealRepository.getAppealsByUserId(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void getAppealsByWeatherCity() {
        Weather weather = new Weather();
        weather.setEmail("test@mail.ru");
        weather.setCity("Kazan");
        weather.setTemp("-6");
        weather.setHumidity("75");
        weatherRepository.save(weather);

        Appeal appeal = new Appeal();
        appeal.setWeather(weather);
        testEntityManager.persistAndFlush(weather);

        List<Appeal> result = appealRepository.getAppealsByWeatherCity("Kazan");
        Assert.assertNotNull(result);
    }
}
