package com.netcracker.dto;


public interface MapperDto<T, D> {

  D toEntity(T dto);

  T toDto(D entity);


}