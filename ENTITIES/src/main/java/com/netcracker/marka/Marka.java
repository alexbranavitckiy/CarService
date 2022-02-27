package com.netcracker.marka;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marka {

    private UUID id= UUID.randomUUID();

    private String generation;

    private Date year;

    private double engineSize;


}
