package ru.itis.shagiakhmetova.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.shagiakhmetova.dto.UserDto;
import ru.itis.shagiakhmetova.service.UserService;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/user")
    public Iterable<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable Integer id) {
        return userService.get(id);
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") UserDto userDto, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.signUp(userDto, url);
        return "sign_up_success";
    }

    @GetMapping("/verification")
    public String verify(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }
}

