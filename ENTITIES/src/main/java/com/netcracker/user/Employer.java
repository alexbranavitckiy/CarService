package com.netcracker.user;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer   {

    private UUID id= UUID.randomUUID();

    private String name;

    private String phone;

    private String mail;

    private String descriptions;

    private Role role;

    private String login;

    private String password;

    private String homeAddress;

    private Qualification qualificationEnum;

    private String education;



}
