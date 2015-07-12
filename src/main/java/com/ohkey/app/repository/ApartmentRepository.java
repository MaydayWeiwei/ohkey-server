package com.ohkey.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohkey.app.model.Apartment;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {

}
