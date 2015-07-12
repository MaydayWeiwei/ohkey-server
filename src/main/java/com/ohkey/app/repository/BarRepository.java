package com.ohkey.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohkey.app.model.Bar;

@Repository
public interface BarRepository extends CrudRepository<Bar, Integer> {

}
