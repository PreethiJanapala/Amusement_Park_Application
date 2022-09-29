package com.capg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Activity;
@Repository
public interface ActivityDao extends CrudRepository<Activity, Integer> {
	
	public Activity findByActivityUIN(Integer activityUin);
}
