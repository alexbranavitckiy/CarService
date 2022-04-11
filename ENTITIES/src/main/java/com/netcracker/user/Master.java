package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.outfit.Outfit;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "master")
public class Master extends Employers  {

 @OneToMany(mappedBy = "master")
 private List<Outfit> outfits;

}
