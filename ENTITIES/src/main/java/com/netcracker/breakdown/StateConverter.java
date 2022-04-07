package com.netcracker.breakdown;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<State, String> {

 @Override
 public String convertToDatabaseColumn(State category) {
  if (category == null) {
   return null;
  }
  return category.getCode();
 }

 @Override
 public State convertToEntityAttribute(String code) {
  if (code == null) {
   return null;
  }
  return Stream.of(State.values())
   .filter(c -> c.getCode().equals(code))
   .findFirst()
   .orElseThrow(IllegalArgumentException::new);
 }

}