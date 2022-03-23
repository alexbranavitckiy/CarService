package com.netcracker.dto;

import java.util.UUID;


public interface MapperDto<T, D> {

  D toEntity(T dto);

  T toDto(D entity);


}