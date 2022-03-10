package com.netcracker.marka;

import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.user.Client;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "id" })
public class CarClient {

    private UUID id;

    private String summer;

    private String ear;

    private String metadataCar;

    private String run;

    private List<CarBreakdown> carBreakdownList;

    private Marka marka;


    @Override
    public String toString() {
        return "Your cars: summer='" + summer + '\'' +
                ", ear='" + ear + '\'' +
                ", metadataCar='" + metadataCar + '\'' +
                ", run='" + run + '\'' +
                '}';
    }

}
