package com.navtuan12.job_seeker_server.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank(message = "FIRSTNAME_INVALID")
    private String firstName;

    @NotBlank(message = "LASTNAME_INVALID")
    private String lastName;

    @Email(message = "EMAIL_INVALID")
    private String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
}
