package com.ohkey.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohkey.app.model.Code;

@Repository
public interface CodeRepository extends CrudRepository<Code, Integer> {

}
