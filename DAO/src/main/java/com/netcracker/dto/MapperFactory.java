package com.netcracker.dto;

import com.netcracker.dto.convector.ClientConvector;
import com.netcracker.user.Client;

public class MapperFactory<T, Dto> {

  public Object getDoughnut(TypeMapper type, T o, Dto dto) {
    switch (type) {
      case CLIENT_CONVECTOR:
        if (dto instanceof Client) {
          return new ClientConvector().toDto((Client) dto);
        }
        break;
      case ENUM_CONVECTOR:

        break;
      default:
        throw new IllegalArgumentException("Wrong doughnut type:" + type);
    }
    throw new IllegalArgumentException("Wrong doughnut type:" + type);
  }


}
