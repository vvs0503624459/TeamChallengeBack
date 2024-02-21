package team.challenge.MobileStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PasswordResetRequest(
        String token,

        @NotBlank(message = "Password must not be blank!")
        @Pattern(regexp = ".*[!@#$%^&*()_+\\-=].*",
                message = "Must contain at least one special symbol ( !@#$%^&*()_+-= )!")
        @Pattern(regexp = ".*\\d.*",
                message = "Must contain at least one digit!")
        @Pattern(regexp = ".*[A-Z].*",
                message = "Must contain at least one uppercase letter!")
        @Pattern(regexp = ".*[a-z].*",
                message = "Must contain at least one lowercase letter!")
        @Size(min = 6, message = "Must be minimum 6 symbols long!")
        @Size(max = 16, message = "Must be maximum 16 symbols long!")
        String password
) {
}
