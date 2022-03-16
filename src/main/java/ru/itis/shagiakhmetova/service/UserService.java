package ru.itis.shagiakhmetova.service;

import ru.itis.shagiakhmetova.dto.UserDto;

public interface UserService {
    UserDto signUp(UserDto createUserDto, String url);
    Iterable<UserDto> getAll();
    UserDto get(Integer id);
    public UserDto getByEmail(String email);
    boolean verify(String verificationCode);
    void sendVerificationMail(String mail, String name, String code, String url);
}
