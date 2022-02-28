

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netcracker.marka.CarClient;
import com.netcracker.order.Order;
import com.netcracker.time.Entry;
import com.netcracker.user.*;
import servisec.Impl.UserServicesImpl;
import servisec.UserServices;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Main {


    public static void main(String[] arg)  {

       // ObjectMapper objectMapper = new ObjectMapper();
        //        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        //
        //
        //        UserServices userServices = new UserServicesImpl(new File("src/main/resources/entry.json"));
        //
        //        System.out.println(  userServices.getUserByName("Zulauf, Stracke and Zboncak",objectMapper));


        //        JsonNode jsonNode=objectMapper.readTree(new File("src/main/resources/entry.json"));


//        System.out.println(jsonNode);


        //     objectMapper.writeValue(new File("src/main/resources/entry.json"), master);
        //        objectMapper.writeValue(new File("src/main/resources/entry.json"), master);


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

