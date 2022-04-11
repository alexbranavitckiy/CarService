package com.netcracker.outfit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.order.Orders;
import com.netcracker.user.Master;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "outfit")
public class Outfit  {

    @Id
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID id;

    @Length(min = 1, max = 100)
    @Column(name = "name",columnDefinition = "VARCHAR(100) not null ")
    private String name;

    private String description;

    @OneToOne (optional=false, mappedBy="outfit")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "id_master")
    private Master master;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnt;

    @Range(min = 0, max = 10000)
    private double price;

    @Column(name = "state_outfit")
    private State stateOutfit;

}
