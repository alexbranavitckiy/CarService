package com.netcracker.car;

import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.order.Orders;
import com.netcracker.user.Clients;
import lombok.*;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Entity(name = "car_client")
public class CarClient {

 @Id
 @org.hibernate.annotations.Type(type="pg-uuid")
 private UUID id;

 @Column(name = "summary")
 private String summary;

 @Column(name = "description")
 private String description;

 @Column(name = "ear")
 private Date ear;

 @Column(name = "metadata_car")
 private String metadataCar;

 @Column(name = "run")
 private String run;

 @ManyToOne
 @JoinColumn(name = "id_clients")
 private Clients client;

 @OneToMany(mappedBy = "carClient")
 private List<Orders> order;

 @OneToMany(mappedBy = "carClient")
 private List<CarBreakdown> carBreakdowns;

 @ManyToOne
 @JoinColumn(name = "id_mark")
 private Mark mark;

}
