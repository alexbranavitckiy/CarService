package com.netcracker.car;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "model_car")
public class ModelCar {

 @Id
 private UUID id;

 @Column(name = "name")
 private String name;

 @Column(name = "year_start")
 private Date yearStart;

 @Column(name = "year_end")
 private Date yearEnd;

}
