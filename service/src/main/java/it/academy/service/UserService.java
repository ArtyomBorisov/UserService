package it.academy.service;

import it.academy.dto.UserRequestDto;
import it.academy.dto.UserResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void add(UserRequestDto userRequestDto);
    List<UserResponseDto> getAll();
    Page<UserResponseDto> getPage(int page);
}
