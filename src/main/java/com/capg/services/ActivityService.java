package com.capg.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capg.entity.Activity;

public interface ActivityService {
	public ResponseEntity<?> addActivity(Activity activity);
	
	public Iterable<Activity> displayAllActivitys();
	
	public Activity updateActivity(Activity activity);
	
	
	public boolean checkDataExistence(Integer activity);

	public ResponseEntity<?> findActivityById(Integer id);

	public String deleteActivityById(Integer id);

	public List<Activity> addAllActivitys(List<Activity> activityList);
}
