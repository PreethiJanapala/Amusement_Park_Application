package com.capg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.User;
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

}
