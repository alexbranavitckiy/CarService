package date.outfit;

import date.order.Order;
import date.time.Entry;
import date.user.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outfit {

    private String name;

    private String descriptions;

    private Order order;

    private List<Employer> employers;

    private Date dateStart;

    private Date dateEnt;

    private double price;

    private  StateOutfit stateOutfit;

}
