package com.netcracker.car;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "mark")
public class Mark  {

 @Id
 private UUID id;

 private String name;

 @Column(name = "year_start")
 private Date yearStart;

 @Column(name = "year_end")
 private Date yearEnd;

}
