package com.netcracker.user;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleUserConverter implements AttributeConverter<RoleUser, String> {

 @Override
 public String convertToDatabaseColumn(RoleUser state) {
  if (state == null) {
   return null;
  }
  return state.getCode();
 }


 @Override
 public RoleUser convertToEntityAttribute(String code) {
  if (code == null) {
   return null;
  }
  return Stream.of(RoleUser.values())
   .filter(c -> c.getCode().equals(code))
   .findFirst()
   .orElseThrow(IllegalArgumentException::new);
 }

}