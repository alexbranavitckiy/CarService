package com.netcracker.user;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class QualificationConverter implements AttributeConverter<Qualification, String> {

 @Override
 public String convertToDatabaseColumn(Qualification state) {
  if (state == null) {
   return null;
  }
  return state.getCode();
 }


 @Override
 public Qualification convertToEntityAttribute(String code) {
  if (code == null) {
   return null;
  }
  return Stream.of(Qualification.values())
   .filter(c -> c.getCode().equals(code))
   .findFirst()
   .orElseThrow(IllegalArgumentException::new);
 }

}