package com.netcracker.marka;

import com.netcracker.breakdown.CarBreakdown;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;
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


    public String toStringClientWithoutMark() {
        return "Summer='" + summer + '\'' +
                ", ear='" + ear + '\'' +
                ", metadataCar='" + metadataCar + '\'' +
                ", run=" + run;
    }


    public String toStringCarBreakdownList() {
        return " Summer='" + summer + '\'' +
                ", ear='" + ear + '\'' +
                ", metadataCar='" + metadataCar + '\'' +
                ", run=" + run +
                ", carBreakdownList=" + carBreakdownList +
                ", marka=" + marka;
    }

    public String toStringClient() {
        return "Summer='" + summer + '\'' +
                ", ear='" + ear + '\'' +
                ", metadataCar='" + metadataCar + '\'' +
                ", run=" + run +
                ", marka=" + marka;
    }
}
