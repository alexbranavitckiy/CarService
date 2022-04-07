package com.netcracker.outfit;



import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<com.netcracker.outfit.State, String> {

 @Override
 public String convertToDatabaseColumn(com.netcracker.outfit.State state) {
  if (state == null) {
   return null;
  }
  return state.getCode();
 }


 @Override
 public com.netcracker.outfit.State convertToEntityAttribute(String code) {
  if (code == null) {
   return null;
  }
  return Stream.of(State.values())
   .filter(c -> c.getCode().equals(code))
   .findFirst()
   .orElseThrow(IllegalArgumentException::new);
 }

}