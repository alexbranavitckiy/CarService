

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
import java.util.*;


public class Main {


    public static void main(String[] arg) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//skip unknown fields
    }
}

