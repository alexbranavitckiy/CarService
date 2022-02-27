package com.netcracker.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private UUID id = UUID.randomUUID();

    private String name;

    private String phone;

    private String mail;

    private String description;

    private String login;

    private String password;

    private RoleUser roleUser;


}
