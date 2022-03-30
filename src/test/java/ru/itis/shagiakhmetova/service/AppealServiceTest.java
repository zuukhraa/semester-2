package ru.itis.shagiakhmetova.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.shagiakhmetova.dto.AppealDto;
import ru.itis.shagiakhmetova.repository.AppealRepository;
import java.util.List;

@RunWith(SpringRunner.class)
public class AppealServiceTest {

    @TestConfiguration
    static class AppealServiceTestContextConfiguration {

        @MockBean
        private AppealRepository appealRepository;

        @Bean
        public AppealService appealService() {
            return new AppealServiceImpl(appealRepository);
        }
    }
    @Autowired
    private AppealService appealService;

    @Test
    public void testGetAppealsByUser() {
        List<AppealDto> result = appealService.getAppealsByUser(1);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAppealsByCity() {
        List<AppealDto> result = appealService.getAppealsByCity("Kazan");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }
}
