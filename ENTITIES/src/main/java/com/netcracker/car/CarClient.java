package com.netcracker.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.order.Order;
import com.netcracker.user.Client;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;


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
 @org.hibernate.annotations.Type(type = "pg-uuid")
 private UUID id;

 @Column(name = "summary")
 private String summary;

 @Column(name = "description")
 private String description;

 @Column(name = "year")
 private Date year;

 @Column(name = "metadata_car", columnDefinition = "VARCHAR(80) not null unique")
 private String metadataCar;

 @Column(name = "run")
 @Range(min = 0, max = 1000000)
 private int run;

 @ApiModelProperty(hidden = true)
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "id_clients")
 private Client client;

 @ApiModelProperty(hidden = true)
 @OneToMany(mappedBy = "carClient", fetch = FetchType.LAZY)
 private List<Order> order;

 @ApiModelProperty(hidden = true)
 @OneToMany(mappedBy = "carClient", fetch = FetchType.LAZY)
 private List<CarBreakdown> carBreakdowns;

 @ManyToOne
 @JoinColumn(name = "id_mark")
 private Mark mark;

}
