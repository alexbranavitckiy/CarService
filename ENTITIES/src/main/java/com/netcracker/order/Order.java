package com.netcracker.order;

import com.netcracker.car.CarClient;
import com.netcracker.outfit.Outfit;
import com.netcracker.user.Master;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "orders")
public class Order {

 @Id
 private UUID id;

 @Column(name = "description")
 private String description;

 @OneToOne
 @JoinColumn(name = "id_outfits")
 private Outfit outfit;

 @ManyToMany
 @JoinColumn(name = "id_masters")
 private List<Master> masterReceiver;

 @ManyToOne
 @JoinColumn(name = "id_car")
 private CarClient carClient;

 private State state;

 @Column(name = "created_date")
 private Date createdDate;

 @Column(name = "updated_date")
 private Date updatedDate;

}
