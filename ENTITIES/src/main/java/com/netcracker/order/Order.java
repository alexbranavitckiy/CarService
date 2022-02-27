package com.netcracker.order;

import com.netcracker.user.Client;
import com.netcracker.outfit.Outfit;
import com.netcracker.time.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private UUID id= UUID.randomUUID();
    private String descriptions;
    private List<Outfit> outfits;
    private double priceSum;
    private Client client;
    private StateOrder stateOrder;
    private List<Entry> entry;
    private Date dateCreat;
    private Date dateUpdate;


}
