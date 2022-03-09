package ru.itis.shagiakhmetova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.shagiakhmetova.dto.UserDto;
import ru.itis.shagiakhmetova.model.User;
import ru.itis.shagiakhmetova.repository.UserRepository;
import java.util.stream.Collectors;
import static ru.itis.shagiakhmetova.dto.UserDto.from;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    public UserDto save(UserDto createUserDto) {
        User newUser = User.builder()
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .password(encoder.encode(createUserDto.getPassword()))
                .build();
        return from(userRepository.save(newUser));
    }

    @Override
    public Iterable<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::from).collect(Collectors.toList());
    }

    @Override
    public UserDto get(Integer id) {
        return userRepository.findById(id).stream().map(UserDto::from).findFirst().orElse(null);
    }

    @Override
    public UserDto getByEmail(String email) {
        return userRepository.getUserByEmail(email).stream().map(UserDto::from).findFirst().orElse(null);
    }
}
