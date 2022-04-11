package com.netcracker.DTO;

public interface MapperDto<Entity, Dto> {

 Entity toEntity(Dto dto);

 Dto toDto(Entity dto);

}
