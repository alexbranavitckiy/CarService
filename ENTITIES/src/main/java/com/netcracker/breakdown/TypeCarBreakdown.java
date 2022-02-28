package com.netcracker.breakdown;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeCarBreakdown {

    private UUID id;

    private String name;

    private String location;

   }
