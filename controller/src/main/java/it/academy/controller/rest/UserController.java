package it.academy.controller.rest;

import it.academy.dto.UserRequestDto;
import it.academy.dto.UserResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import it.academy.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid UserRequestDto dto) {
        userService.add(dto);
    }

    @GetMapping("/all")
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @GetMapping
    public Page<UserResponseDto> get(@RequestParam @Min(value = 0) int page) {
        return userService.getPage(page);
    }
}
