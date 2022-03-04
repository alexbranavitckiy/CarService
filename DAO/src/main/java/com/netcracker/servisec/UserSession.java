package com.netcracker.servisec;

import com.netcracker.user.Client;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;

public class UserSession {

    private static MasterReceiver masterReceiverSession;
    private static Master masterSession;
    private static Client clientSession;


    private UserSession() {
    }

    public static void openSession(Object object) {
        if (clientSession == null && object instanceof Client) {
            clientSession = (Client) object;
            closeSession(masterSession, masterReceiverSession);
        }
        if (masterSession == null && object instanceof Master) {
            masterSession = (Master) object;
            closeSession(clientSession, masterReceiverSession);
        }
        if (masterReceiverSession == null && object instanceof MasterReceiver) {
            masterReceiverSession = (MasterReceiver) object;
            closeSession(clientSession, masterSession);
        }
    }

    public static void closeSession(Object... o) {
        Arrays.stream(o).forEach(x -> x = null);
    }

    public static void closeSession() {
      closeSession(masterReceiverSession,masterSession,clientSession);
    }

    public static Optional<MasterReceiver> getMasterReceiverSession() {//return immutable object
        return Optional.ofNullable(masterReceiverSession);
    }

    public static Optional<Master> getMasterSession() {//return immutable object
        return Optional.ofNullable(masterSession);
    }

    public static Optional<Client> getClientSession() {//return immutable object
        return Optional.ofNullable(clientSession);
    }
}



