package ru.itis.shagiakhmetova.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Optional;

@Controller
public class HelloController {

    @Operation(summary = "Returns hello message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message was received",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("name"));
    }

    @Operation(summary = "Returns index page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Index page was received",
                    content = {
                            @Content(mediaType = "text/html")
                    }
            )
    })
    @GetMapping("")
    public String getIndexPage() {
        return "index";
    }


    @Operation(summary = "Returns home page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Home page was received",
                    content = {
                            @Content(mediaType = "text/html")
                    }
            )
    })
    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
}
