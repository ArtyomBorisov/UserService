package it.academy.utils;

import it.academy.dto.UserRequestDto;
import it.academy.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDtoToEntityConverter implements Converter<UserRequestDto, UserEntity> {
    @Override
    public UserEntity convert(UserRequestDto dto) {
        return UserEntity.Builder.createBuilder()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setMiddleName(dto.getMiddleName())
                .setEmail(dto.getEmail())
                .build();
    }
}
