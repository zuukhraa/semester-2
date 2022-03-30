package ru.itis.shagiakhmetova.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.shagiakhmetova.model.User;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testPassword");
        testEntityManager.persistAndFlush(user);
        Optional<User> result = userRepository.getUserByEmail("test@mail.ru");
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void testFindUserByVerificationCode() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testPassword");
        user.setVerificationCode("testCode");
        testEntityManager.persistAndFlush(user);
        Optional<User> result = Optional.ofNullable(userRepository.findByVerificationCode("testCode"));
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void testFindAllByName() {
        List<User> result = userRepository.findAllByName("Ivan");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }
}
