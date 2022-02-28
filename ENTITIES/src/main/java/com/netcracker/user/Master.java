package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonRootName;
import com.netcracker.outfit.Outfit;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Master extends Employer  {

    private List<Outfit> outfits;




}
