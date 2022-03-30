package ru.itis.shagiakhmetova.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.shagiakhmetova.dto.AppealDto;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.model.Appeal;
import ru.itis.shagiakhmetova.model.User;
import ru.itis.shagiakhmetova.model.Weather;
import ru.itis.shagiakhmetova.repository.AppealRepository;
import ru.itis.shagiakhmetova.repository.UserRepository;
import ru.itis.shagiakhmetova.repository.WeatherRepository;
import ru.itis.shagiakhmetova.service.AppealService;
import ru.itis.shagiakhmetova.service.MainService;
import ru.itis.shagiakhmetova.service.UserService;
import java.util.Arrays;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MainService mainService;

    @MockBean
    private UserService userService;

    @MockBean
    private AppealService appealService;

    @MockBean
    private WeatherRepository weatherRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AppealRepository appealRepository;

    @Before
    public void init() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testPassword");


        Weather weather = new Weather();
        weather.setEmail("test@mail.ru");
        weather.setCity("Kazan");

        Appeal appeal = new Appeal();
        appeal.setId(1);
        appeal.setWeather(weather);
        appeal.setUser(user);

        given(mainService.getAll()).willReturn(Arrays.asList(WeatherDto.fromModel(weather)));
        given(mainService.getWeatherByCity("Kazan")).willReturn(Arrays.asList(WeatherDto.fromModel(weather)));
        given(appealService.getAppealsByUser(1)).willReturn(Arrays.asList(AppealDto.fromModel(appeal)));
        given(appealService.getAppealsByCity("Kazan")).willReturn(Arrays.asList(AppealDto.fromModel(appeal)));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/allWeather")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city").value("Kazan"));
    }

    @Test
    public void testGetWeatherByCity() throws Exception {
        mockMvc.perform(get("/weatherByCity/Kazan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city").value("Kazan"));
    }

    @Test
    public void testGetAppealsByUser() throws Exception {
        mockMvc.perform(get("/appeals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void testGetAppealsByCity() throws Exception {
        mockMvc.perform(get("/city/Kazan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

}
