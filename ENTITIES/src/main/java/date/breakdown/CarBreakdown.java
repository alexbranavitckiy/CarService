package date.breakdown;


import date.marka.CarClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarBreakdown {

    private CarClient carClient;

    private List<TypeCarBreakdown> typeCarBreakdowns;

    private String descriptions;

    private StateCarBreakdown stateCarBreakdown;
}
