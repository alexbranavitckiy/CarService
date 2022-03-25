package com.netcracker.jdbc.services.impl.client;

import com.netcracker.ClientServices;
import com.netcracker.LoginServices;
import com.netcracker.dto.modelDTO.ClientDto;
import com.netcracker.errors.PersistException;
import com.netcracker.file.services.impl.LoginServicesImpl;
import com.netcracker.jdbc.services.CarDao;
import com.netcracker.jdbc.services.ClientDao;
import com.netcracker.jdbc.services.impl.CarClientDaoImpl;
import com.netcracker.jdbc.services.impl.ClientDaoImpl;
import com.netcracker.marka.CarClient;
import com.netcracker.session.UserSession;
import com.netcracker.user.Client;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class ClientDaoServicesImpl implements ClientServices {

    private final LoginServices loginService = new LoginServicesImpl();
    private final ClientDao clientDao = new ClientDaoImpl();
    private final CarDao carClientDao = new CarClientDaoImpl();
    private List<CarClient> carClients;

    @Override
    @SneakyThrows
    public List<ClientDto> getAllClient() {
        List<CarClient> carClients = carClientDao.getAll();
        List<ClientDto> clientDto = new ArrayList<>();
        clientDao.getAll().forEach(x ->
                clientDto.add(this.parserClientDto(x, carClients.stream().filter(z -> z.getId_clients().equals(x.getId())).collect(Collectors.toList()))));
        return clientDto;
    }

    @SneakyThrows
    public List<CarClient> getCarByIdClient(UUID uuidClient) {
        return carClientDao.getAllCarClientByIdClient(uuidClient);
    }

    @SneakyThrows
    public boolean addObjectInClient(ClientDto clientDto) {
        this.clientDao.addObject(this.parserClientAndCarClient(clientDto));
        this.carClients.forEach(x-> {
            try {
                this.carClientDao.addObject(x);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    @Override
    public boolean updateClient(ClientDto client) {

        return false;
    }

    @Override
    public boolean updateClientNotSession(ClientDto client) {
        return false;
    }

    @Override
    public boolean updateClientCar(CarClient carClient) {
        ClientDto client = UserSession.getCloneClientSession();
        client.setCarClients(client.getCarClients().stream()
                .filter(x -> x.getId().toString().equalsIgnoreCase(carClient.getId().toString()))
                .collect(Collectors.toList()));
        return this.updateClient(client);
    }

    @Override
    public boolean addObjectInClientNotOnline(ClientDto client) {

        return false;
    }

    private boolean passwordCheck(ClientDto client) {
        try {
            if (loginService.searchByUserLoginAndPassword(client.getLogin(),
                    client.getPassword())) {
                log.info("The username you entered is already taken");
                return false;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return true;
    }


    Client parserClientAndCarClient(ClientDto clientDto) {
        Client clients = Client.builder()
                .description(clientDto.getDescription())
                .email(clientDto.getEmail())
                .login(clientDto.getLogin())
                .orders(clientDto.getOrders())
                .name(clientDto.getName())
                .phone(clientDto.getPhone())
                .password(clientDto.getPassword())
                .id(clientDto.getId())
                .build();
        this.carClients = clientDto.getCarClients();
        if (clientDto.getCarClients() != null && !clientDto.getCarClients().isEmpty())
            clients.setCarClients(clientDto.getCarClients().stream().map(CarClient::getId).collect(Collectors.toList()));
        else clients.setCarClients(new ArrayList<>());
        return clients;
    }

    ClientDto parserClientDto(Client client, List<CarClient> carClients) {
        ClientDto clientDto = ClientDto.builder()
                .roleUser(client.getRoleUser())
                .description(client.getDescription())
                .name(client.getName())
                .phone(client.getPhone())
                .password(client.getPassword())
                .login(client.getLogin())
                .carClients(new ArrayList<>())
                .email(client.getEmail())
                .id(client.getId())
                .orders(client.getOrders()).build();
        if (carClients != null && !carClients.isEmpty()) {
            clientDto.setCarClients(carClients);
        }
        return clientDto;
    }
}