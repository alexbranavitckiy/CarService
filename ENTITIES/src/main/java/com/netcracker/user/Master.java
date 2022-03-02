package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.netcracker.outfit.Outfit;
import lombok.*;

import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Master extends Employer  {

    private List<Outfit> outfits;

    @Override
    public String toString() {
        return "Master{" +
                "outfits=" + outfits +
                "} " + super.toString();
    }
}
