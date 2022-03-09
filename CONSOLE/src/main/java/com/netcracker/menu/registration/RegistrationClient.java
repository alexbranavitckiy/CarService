package com.netcracker.menu.registration;

import com.netcracker.menu.Menu;
import com.netcracker.menu.car.NewCarClient;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

@Slf4j
public class RegistrationClient implements Menu {

    private boolean flag = true;
    private final ClientServices clientServices = new ClientServicesImpl();


    @Override
    public void preMessage(String parentsName) {
        log.info("Enter 1 {}" , parentsName);
        log.info("Enter 2 to continue registration");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    Client client = new Client();
                    log.info("Enter login");
                    client.setLogin(in.next());
                    log.info("Enter password");
                    client.setPassword(in.next());
                    log.info("Enter phone");
                    client.setPhone(in.next());
                    log.info("Filling in car details");
                    NewCarClient carClient = new NewCarClient();
                    carClient.run(in, "");
                    if (carClient.getCarClient().isPresent()) {
                        client.setCarClients(new HashSet<>());
                        client.getCarClients().add((carClient.getCarClient().get()));
                    } else {
                        log.info("Try again to enter information");
                        this.preMessage(parentsName);
                        break;
                    }
                    client.setId(UUID.randomUUID());
                    client.setRoleuser(RoleUser.REGISTERED);
                    if (clientServices.addObjectInClient(client)) {
                        log.info("User created successfully");
                        this.flag = false;
                    } else {
                        log.info("Invalid data. Repeat registration");
                        this.preMessage(parentsName);
                    }
                    break;
                }
                case "1": {
                    this.flag = false;
                    break;
                }
                default: {
                    this.preMessage(parentsName);
                    break;
                }
            }
        }
    }
}
