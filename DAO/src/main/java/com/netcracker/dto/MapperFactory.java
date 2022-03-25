package com.netcracker.dto;

import com.netcracker.user.Client;

public class MapperFactory<T, Dto> {

 public Object getDoughnut(TypeMapper type, T o, Dto dto) throws IllegalArgumentException {
  switch (type) {
   case CLIENT_CONVECTOR:
    if (dto instanceof Client) {
     //TODO!!!
     break;
    }
    break;
   case ENUM_CONVECTOR:
    //TODO!!!
    break;
   default:
    throw new IllegalArgumentException("Wrong doughnut type:" + type);
  }
  throw new IllegalArgumentException("Wrong doughnut type:" + type);
 }

}
