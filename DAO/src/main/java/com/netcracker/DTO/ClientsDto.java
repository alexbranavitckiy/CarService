package com.netcracker.DTO;

import javax.persistence.Id;
import java.util.UUID;

public class ClientsDto {

 @Id
 @org.hibernate.annotations.Type(type = "pg-uuid")
 private UUID id;

 private String name;

 private String phone;

 private String email;

 private String description;

}
