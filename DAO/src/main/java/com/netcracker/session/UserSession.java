package com.netcracker.session;

import com.netcracker.dto.model.ClientDto;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;

import java.util.*;

public class UserSession {

  private static MasterReceiver masterReceiverSession;
  private static Master masterSession;
  private static ClientDto clientSession;

  private UserSession() {
  }

  public static void openSession(Object object) {
    if (clientSession == null && object instanceof ClientDto) {
      clientSession = (ClientDto) object;
      closeSession(masterSession, masterReceiverSession);
      return;
    }
    if (masterSession == null && object instanceof Master) {
      masterSession = (Master) object;
      closeSession(clientSession, masterReceiverSession);
      return;
    }
    if (masterReceiverSession == null && object instanceof MasterReceiver) {
      masterReceiverSession = (MasterReceiver) object;
      closeSession(clientSession, masterSession);
    }
  }

  public static boolean updateSession(Object object) {
    if (object instanceof ClientDto) {
      clientSession = UserSession.getCloneClient((ClientDto) object);
      closeSession(masterSession, masterReceiverSession);
      return true;
    }
    if (object instanceof Master) {
      masterSession = (Master) object;
      closeSession(clientSession, masterReceiverSession);
      return true;
    }
    if (object instanceof MasterReceiver) {
      masterReceiverSession = (MasterReceiver) object;
      closeSession(clientSession, masterSession);
      return true;
    }
    return false;
  }

  public static void closeSession(Object... o) {
    Arrays.stream(o).forEach(x -> {
      if (x instanceof ClientDto) {
        clientSession = (ClientDto) x;
        masterSession = null;
        masterReceiverSession = null;
      }
      if (x instanceof Master) {
        masterSession = (Master) x;
        clientSession = null;
        masterReceiverSession = null;
      }
      if (x instanceof MasterReceiver) {
        masterReceiverSession = (MasterReceiver) x;
        clientSession = null;
        masterSession = null;
      }
    });
  }

  public static void closeSession() {
    masterSession = null;
    masterReceiverSession = null;
    clientSession = null;
  }

  public static Optional<MasterReceiver> getMasterReceiverSession() {//return immutable object
    return Optional.ofNullable(masterReceiverSession);
  }

  public static Optional<Master> getMasterSession() {//return immutable object
    return Optional.ofNullable(masterSession);
  }

  public static MasterReceiver getCloneMasterReceiverSession() {
    return MasterReceiver.builder()
      .description(masterReceiverSession.getDescription())
      .education(masterReceiverSession.getEducation())
      .homeAddress(masterReceiverSession.getHomeAddress())
      .id(masterReceiverSession.getId())
      .login(masterReceiverSession.getLogin())
      .role(masterReceiverSession.getRole())
      .qualificationEnum(masterReceiverSession.getQualificationEnum())
      .mail(masterReceiverSession.getMail())
      .homeAddress(masterReceiverSession.getHomeAddress())
      .password(masterReceiverSession.getPassword())
      .name(masterReceiverSession.getName())
      .orders(new ArrayList<>(masterReceiverSession.getOrders())).build();
  }

  public static ClientDto getCloneClient(ClientDto client) {
    return ClientDto.builder()
      .carClients(client.getCarClients())
      .description(client.getDescription())
      .id(client.getId())
      .email(client.getEmail())
      .name(client.getName())
      .password(client.getPassword())
      .roleuser(client.getRoleuser())
      .phone(client.getPhone())
      .login(client.getLogin())
      .carClients(new ArrayList<>(client.getCarClients()))
      .build();
  }

  public static ClientDto getCloneClientSession() {
    return ClientDto.builder()
      .carClients(clientSession.getCarClients())
      .description(clientSession.getDescription())
      .id(clientSession.getId())
      .email(clientSession.getEmail())
      .name(clientSession.getName())
      .password(clientSession.getPassword())
      .roleuser(clientSession.getRoleuser())
      .phone(clientSession.getPhone())
      .login(clientSession.getLogin())
      .carClients(new ArrayList<>(clientSession.getCarClients()))
      .build();
  }

  public static Optional<ClientDto> getClientSession() {
    if (clientSession != null) {
      return Optional.ofNullable(getCloneClient(clientSession));
    }
    return Optional.empty();
  }
}


