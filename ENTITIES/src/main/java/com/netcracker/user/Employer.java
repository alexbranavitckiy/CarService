package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = MasterReceiver.class, name = "masterReceiver")
//})
public class Employer {

    private UUID id;

    private String name;

    private String phone;

    private String mail;

    private String description;

    private Role role;

    private String login;

    private String password;

    private String homeAddress;

    private Qualification qualificationEnum;

    private String education;


}
