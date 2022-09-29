package com.capg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Admin;

@Repository
public interface AdminDao extends CrudRepository<Admin, Integer>{

}
