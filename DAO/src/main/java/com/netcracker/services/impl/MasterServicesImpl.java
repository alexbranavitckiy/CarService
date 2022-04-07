package com.netcracker.services.impl;


import com.netcracker.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterServicesImpl {

 private final MasterRepository masterRepository;


 @Autowired
 private MasterServicesImpl(MasterRepository masterRepository) {
  this.masterRepository = masterRepository;
 }


}
