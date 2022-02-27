package com.netcracker.marka;

import com.netcracker.breakdown.CarBreakdown;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarClient   {

    private String summer;

    private Date ear;

    private Double run;

    private List<CarBreakdown> carBreakdownList;

    private Marka marka;

   }
