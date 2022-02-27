package com.netcracker.user;

import com.netcracker.marka.CarClient;
import com.netcracker.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User{

    private UUID id= UUID.randomUUID();

    private List<Order> orders;

    private List<CarClient> carClients;

}
