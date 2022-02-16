package ru.itis.shagiakhmetova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shagiakhmetova.dto.UserDto;
import ru.itis.shagiakhmetova.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDto findByEmail(String email);
}