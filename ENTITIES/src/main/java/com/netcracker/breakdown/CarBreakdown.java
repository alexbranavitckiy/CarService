package com.netcracker.breakdown;


import com.netcracker.car.CarClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "car_breakdown")
public class CarBreakdown {

 @Id
 @org.hibernate.annotations.Type(type = "pg-uuid")
 private UUID id;

 @ManyToOne
 @JoinColumn(name = "id_car")
 private CarClient carClient;

 @Column(name = "description")
 private String description;

 @Column(name = "run_car_size")
 private String runCarSize;

 private State state;

 private String location;

}
