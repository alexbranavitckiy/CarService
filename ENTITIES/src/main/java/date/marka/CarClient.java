package date.marka;

import date.breakdown.CarBreakdown;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CarClient  extends Marka{

    private String summer;

    private Date ear;

    private Double run;

    private List<CarBreakdown> carBreakdownList;

}
