package com.netcracker.time;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.netcracker.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entry implements Serializable {

    private UUID id = UUID.randomUUID();

    private Date date;

    private String descriptions;


    private Order order;
}
