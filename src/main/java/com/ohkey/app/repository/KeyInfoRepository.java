package com.ohkey.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohkey.app.model.Apartment;
import com.ohkey.app.model.KeyInfo;

@Repository
public interface KeyInfoRepository extends CrudRepository<KeyInfo, Integer> {
	
	List<KeyInfo> findByApartment(Apartment apartment);

}
