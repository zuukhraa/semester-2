package ru.itis.shagiakhmetova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shagiakhmetova.dto.UserDto;
import ru.itis.shagiakhmetova.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    Optional<User> getUserByEmail(String email);
    User findByVerificationCode(String code);
    List<User> findAllByName(String name);
}
