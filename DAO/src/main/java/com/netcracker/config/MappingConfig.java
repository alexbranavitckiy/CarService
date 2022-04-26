package com.netcracker.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class MappingConfig {
 @Bean
 public ModelMapper modelMapper() {
  ModelMapper mapper = new ModelMapper();
  mapper.getConfiguration().
   setMatchingStrategy(MatchingStrategies.STRICT)
   .setFieldMatchingEnabled(true)
   .setSkipNullEnabled(true)
   .setFieldAccessLevel(PRIVATE);
  return new ModelMapper();
 }

 public static <R, E> List<R> convertList(List<E> list, Function<E, R> converter) {
  return list.stream().map(converter).collect(Collectors.toList());
 }



}



