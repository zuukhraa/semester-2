package ru.itis.shagiakhmetova.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.repository.UserRepository;
import ru.itis.shagiakhmetova.repository.WeatherRepository;
import java.util.List;

@RunWith(SpringRunner.class)
public class MainServiceTest {
    @TestConfiguration
    static class MainServiceTestContextConfiguration {

        @MockBean
        private WeatherRepository weatherRepository;

        @MockBean
        private UserRepository userRepository;

        @Bean
        public MainService mainService() {
            return new MainServiceImpl(weatherRepository, userRepository);
        }
    }

    @Autowired
    private MainService mainService;

    @Test
    public void testGetWeatherByCity() {
        List<WeatherDto> result = mainService.getWeatherByCity("Kazan");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAll() {
        List<WeatherDto> result = mainService.getAll();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

}
