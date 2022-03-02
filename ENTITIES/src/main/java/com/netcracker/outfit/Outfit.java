package com.netcracker.outfit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.order.Order;
import com.netcracker.user.MasterReceiver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Outfit {

    private UUID id;

    private String name;

    private String descriptions;

    private Order order;

    private List<MasterReceiver> employers;

    private Date dateStart;

    private Date dateEnt;

    private double price;

    private State stateOutfit;

}
