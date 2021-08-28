package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    @Column
    @NotBlank(message = "Role is mandatory")
    private String role;

}
