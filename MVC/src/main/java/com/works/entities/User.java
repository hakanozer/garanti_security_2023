package com.works.entities;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 200)
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 200)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 2)
    private String selectClass;

}
