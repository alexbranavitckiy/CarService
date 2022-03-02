package com.netcracker.breakdown;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.marka.CarClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;
import java.util.UUID;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CarBreakdown {

    private UUID id;

    private CarClient carClient;

    private List<TypeCarBreakdown> typeCarBreakdowns;

    private String descriptions;

    private Double runCarSize;

    private State stateCarBreakdown;

    }
