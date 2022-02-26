package date.order;

import date.breakdown.CarBreakdown;
import date.outfit.Outfit;
import date.time.Entry;
import date.user.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String descriptions;
    private List<Outfit> outfits;
    private double priceSum;
    private Client client;
    private List<Entry> entrys;
    private List<CarBreakdown> carBreakdowns;



}
