package com.netcracker.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.order.Order;
import lombok.*;


import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MasterReceiver extends Employer {


    private List<Order> orders;


    @Override
    public String toString() {
        return "MasterReceiver{" +
                "orders=" + orders +
                "} " + super.toString();
    }
}

