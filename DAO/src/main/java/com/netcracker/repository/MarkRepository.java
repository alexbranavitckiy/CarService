package com.netcracker.repository;

import com.netcracker.car.Mark;
import com.netcracker.user.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MarkRepository  extends CrudRepository<Mark, UUID> {
}
