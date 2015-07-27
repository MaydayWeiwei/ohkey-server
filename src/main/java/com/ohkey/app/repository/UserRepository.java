package com.ohkey.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohkey.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	List<User> findByLogin(String login);
}
