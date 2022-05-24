package com.netcracker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;


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

 @Autowired
 public void configureJackson(ObjectMapper objectMapper) {
  objectMapper.setTimeZone(TimeZone.getDefault());
 }



}



