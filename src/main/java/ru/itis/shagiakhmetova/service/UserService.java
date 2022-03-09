package ru.itis.shagiakhmetova.service;

import ru.itis.shagiakhmetova.dto.UserDto;

public interface UserService {
    UserDto save(UserDto createUserDto);
    Iterable<UserDto> getAll();
    UserDto get(Integer id);
    public UserDto getByEmail(String email);
}
