package it.academy.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import it.academy.constants.UserRole;
import jakarta.validation.constraints.*;

import static it.academy.constants.MessagesForUsers.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequestDto {

    private final String REGEXP = "[a-zA-Z]*";

    @NotBlank(message = REQUIRED_FIELD)
    @Pattern(regexp = REGEXP, message = ONLY_LETTERS)
    @Size(max = 20, message = MAX_LENGTH + 20)
    private String firstName;

    @NotBlank(message = REQUIRED_FIELD)
    @Pattern(regexp = REGEXP, message = ONLY_LETTERS)
    @Size(max = 40, message = MAX_LENGTH + 40)
    private String lastName;

    @NotBlank(message = REQUIRED_FIELD)
    @Pattern(regexp = REGEXP, message = ONLY_LETTERS)
    @Size(max = 40, message = MAX_LENGTH + 40)
    private String middleName;

    @NotBlank(message = REQUIRED_FIELD)
    @Email(message = NOT_VALID_EMAIL)
    @Size(max = 50, message = MAX_LENGTH + 50)
    private String email;

    @NotNull(message = REQUIRED_FIELD)
    private UserRole role;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }
}
