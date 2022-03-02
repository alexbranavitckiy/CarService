package com.netcracker.servisec;

import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Scanner;

public class ClientServices {


    public void CreateClient(Scanner in) {
        Client c = Client.builder().roleuser(RoleUser.UNREGISTERED).build();


    }


}

