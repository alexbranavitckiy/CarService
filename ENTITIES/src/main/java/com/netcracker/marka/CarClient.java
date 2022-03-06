package com.netcracker.marka;

import com.netcracker.breakdown.CarBreakdown;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarClient carClient = (CarClient) o;
        return Objects.equals(id, carClient.id) && Objects.equals(summer, carClient.summer) && Objects.equals(ear, carClient.ear) && Objects.equals(metadataCar, carClient.metadataCar) && Objects.equals(run, carClient.run) && Objects.equals(carBreakdownList, carClient.carBreakdownList) && Objects.equals(marka, carClient.marka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summer, ear, metadataCar, run, carBreakdownList, marka);
    }
}
