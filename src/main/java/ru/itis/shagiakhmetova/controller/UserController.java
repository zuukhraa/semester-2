package ru.itis.shagiakhmetova.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.shagiakhmetova.dto.UserDto;
import ru.itis.shagiakhmetova.service.UserService;
import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public Iterable<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDto get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @PostMapping("/user")
    public UserDto createUser(@Valid @RequestBody UserDto user) {
        return userService.save(user);
    }
}
