package com.sagar.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static com.sagar.blog.constants.LogMessages.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 3, message = NAME_VALIDATION)
    private String name;

    @NotEmpty
    @Email(message = EMAIL_VALIDATION)
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16, message = PASSWORD_VALIDATION)
    private String password;

    @NotEmpty
    private String about;
}
