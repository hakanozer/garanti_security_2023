package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long uid;

    @Column(unique = true, length = 200)
    @Email
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 200)
    private String email;

    @Column(length = 500)
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 500)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 2)
    private String selectClass;

}
