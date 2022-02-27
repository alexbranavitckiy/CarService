

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.marka.CarClient;
import com.netcracker.order.Order;
import com.netcracker.time.Entry;
import com.netcracker.user.Client;
import com.netcracker.user.MasterReceiver;
import com.netcracker.user.Role;


import java.io.*;
import java.util.Date;
import java.util.List;


public class Main {


    public static void main(String[] arg) {

        //ObjectMapper objectMapper = new ObjectMapper();
        //
        //
        //        objectMapper.writeValue(new File("src/main/resources/entry.json"), "order");
        //
        //        Order entry1 = objectMapper.readValue(new File("src/main/resources/entry.json"), Order.class);

    }

    //public void creat() throws IOException {
    //        ObjectMapper objectMapper = new ObjectMapper();
    //        Entry entry = new Entry();
    //        entry.setDescriptions("adsasd");
    //        entry.setDate(new Date());
    //        MasterReceiver masterReceiver = new MasterReceiver();
    //        masterReceiver.setRole(Role.RECEPTIONIST);
    //        masterReceiver.setMail("alex.ba@hotmail.com");
    //        masterReceiver.setLogin("Alex");
    //        masterReceiver.setPhone("+37533334234324");
    //
    //        Client client = new Client();
    //        client.setLogin("Ivan");
    //        CarClient carClient = new CarClient();
    //        carClient.setSummer("описания машины");
    //        client.setCarClients(List.of(carClient));
    //
    //        Order order = new Order();
    //        order.setClient(client);
    //
    //
    //        objectMapper.writeValue(new File("src/main/resources/entry.json"), order);
    //
    //        Order entry1 = objectMapper.readValue(new File("src/main/resources/entry.json"), Order.class);
    //
    //        System.out.println(entry1);
    //    }

}

