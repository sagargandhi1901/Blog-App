package com.sagar.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 3, message = "Username must be of minimum 3 characters")
    private String name;

    @NotEmpty
    @Email(message = "Email address is not valid.")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16, message = "Password must be of minimum 8 characters and maximum 16 characters")
    private String password;

    @NotEmpty
    private String about;
}
