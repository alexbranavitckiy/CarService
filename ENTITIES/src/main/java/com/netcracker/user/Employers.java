package com.netcracker.user;


import com.netcracker.EntityId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Employers {

    private UUID id;

    private String name;

    private String phone;

    private String mail;

    private String description;

    private Role role;

    private String login;

    private String password;

    private String homeAddress;

    private UUID qualificationEnum;

    private String education;

}
