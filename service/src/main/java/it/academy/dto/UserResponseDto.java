package it.academy.dto;

import it.academy.constants.UserRole;

public class UserResponseDto {
    private String fullName;
    private String email;
    private UserRole role;

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public static class Builder {
        private final UserResponseDto dto;

        public Builder() {
            dto = new UserResponseDto();
        }

        public Builder setFullName(String fullName) {
            dto.fullName = fullName;
            return this;
        }

        public Builder setEmail(String email) {
            dto.email = email;
            return this;
        }

        public Builder setRole(UserRole role) {
            dto.role = role;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public UserResponseDto build() {
            return dto;
        }
    }
}
