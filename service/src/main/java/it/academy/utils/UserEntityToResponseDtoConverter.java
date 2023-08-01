package it.academy.utils;

import it.academy.constants.UserRole;
import it.academy.dto.UserResponseDto;
import it.academy.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToResponseDtoConverter implements Converter<UserEntity, UserResponseDto> {
    @Override
    public UserResponseDto convert(UserEntity entity) {
        String email = entity.getEmail();
        UserRole role = entity.getRoleEntity() != null ? entity.getRoleEntity().getRole() : null;
        String fullName = String.format("%s %s %s",
                entity.getLastName(),
                entity.getFirstName(),
                entity.getMiddleName());

        return UserResponseDto.Builder.createBuilder()
                .setFullName(fullName)
                .setEmail(email)
                .setRole(role)
                .build();
    }
}
