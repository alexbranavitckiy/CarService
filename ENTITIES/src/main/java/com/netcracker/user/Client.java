package com.netcracker.user;

import com.netcracker.marka.CarClient;
import com.netcracker.order.Order;
import lombok.*;


import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {


    private List<Order> orders;

    private List<CarClient> carClients;

    @Builder
    public Client(UUID id, String name, String phone, String email, String description, String login, String password, RoleUser roleuser, List<Order> orders, List<CarClient> carClients) {
        super(id, name, phone, email, description, login, password, roleuser);
        this.orders = orders;
        this.carClients = carClients;
    }

    @Override
    public String toString() {
        return "Client{" +
                ", orders=" + orders +
                ", carClients=" + carClients +
                "} " + super.toString();
    }
}
