package com.netcracker.breakdown;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.car.CarClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;
import java.util.Date;
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

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "id_car")
 private CarClient carClient;

 @Column(name = "description", columnDefinition = "VARCHAR(250) NOT NULL")
 private String description;

 @Range(min = 0, max = 1000000)
 @Column(name = "run_car_size")
 private int runCarSize;

 @Column(name = "update_date")
 private Date updateDate;

 private State state;

 @Column(columnDefinition = "VARCHAR(150) NOT NULL")
 private String location;

}
