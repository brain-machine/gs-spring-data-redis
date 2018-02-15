package io.brainmachine.repositories;

import org.springframework.data.repository.CrudRepository;

import io.brainmachine.entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, String> {}
