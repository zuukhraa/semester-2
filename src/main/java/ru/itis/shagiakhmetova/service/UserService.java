package ru.itis.shagiakhmetova.service;

import ru.itis.shagiakhmetova.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto signUp(UserDto createUserDto, String url);
    List<UserDto> getAll();
    UserDto get(Integer id);
    UserDto getByEmail(String email);
    boolean verify(String verificationCode);
    void sendVerificationMail(String mail, String name, String code, String url);
    List<UserDto> getAllByName(String name);
}
