package ru.itis.shagiakhmetova.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.shagiakhmetova.dto.UserDto;
import ru.itis.shagiakhmetova.dto.WeatherDto;
import ru.itis.shagiakhmetova.service.UserService;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    @Operation(summary = "Returns all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users were received",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = UserDto.class)
                            )
                    }
            )
    })
    @GetMapping("/user")
    @ResponseBody
    public Iterable<UserDto> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "Returns user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was received",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = UserDto.class)
                            )
                    }
            )
    })
    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto getUser(@PathVariable Integer id) {
        return userService.get(id);
    }

    @Operation(summary = "Returns sign up success page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sign up success page was received",
                    content = {
                            @Content(mediaType = "text/html")
                    }
            )
    })
    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") UserDto userDto, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.signUp(userDto, url);
        return "sign_up_success";
    }

    @Operation(summary = "Returns sign up page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sign up page was received",
                    content = {
                            @Content(mediaType = "text/html")
                    }
            )
    })
    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new UserDto());
        return "sign_up";
    }

    @Operation(summary = "Returns verification page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verification page was received",
                    content = {
                            @Content(mediaType = "text/html")
                    }
            )
    })
    @GetMapping("/verification")
    @ResponseBody
    public String verify(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }
}

