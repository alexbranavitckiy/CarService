package com.netcracker.user;


import com.netcracker.order.Order;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "master_receiver")
public class MasterReceiver extends Employers  {

 @ManyToMany(mappedBy = "masterReceiver")
 private List<Order> orders;


}

