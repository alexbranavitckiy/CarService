package com.netcracker.breakdown;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.car.CarClient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entity of car breakdowns")
@Entity(name = "car_breakdown")
public class CarBreakdown {

 @Id
 @Schema(description = "Breakdown ID")
 @org.hibernate.annotations.Type(type = "pg-uuid")
 private UUID id;

 @ManyToOne
 @JsonIgnore
 @JoinColumn(name = "id_car")
 private CarClient carClient;

 @Column(name = "description",columnDefinition = "VARCHAR(250) NOT NULL")
 private String description;

 @Range(min = 0, max = 1000000)
 @Column(name = "run_car_size")
 private int  runCarSize;

 private State state;

 @Column(columnDefinition = "VARCHAR(150) NOT NULL")
 private String location;

}
