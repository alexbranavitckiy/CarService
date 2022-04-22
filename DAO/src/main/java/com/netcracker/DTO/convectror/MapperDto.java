package com.netcracker.DTO.convectror;

public interface MapperDto<T, D> {

 D toEntity(T dto);

 T toDto(D entity);

}
