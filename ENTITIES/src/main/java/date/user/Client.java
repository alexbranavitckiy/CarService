package date.user;

import date.marka.CarClient;
import date.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private List<Order> orders;

    private List<CarClient> carClients;







}
