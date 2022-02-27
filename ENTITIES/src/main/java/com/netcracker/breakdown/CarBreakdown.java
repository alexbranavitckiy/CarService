package com.netcracker.breakdown;


import com.netcracker.marka.CarClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarBreakdown {

    private UUID id= UUID.randomUUID();

    private CarClient carClient;

    private List<TypeCarBreakdown> typeCarBreakdowns;

    private String descriptions;

    private Double runCarSize;

    private State stateCarBreakdown;

    }
