package com.capg.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.ActivityDao;
import com.capg.entity.Activity;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;

@Service
public class ActivityServiceImp implements ActivityService {
	
	//Creating Dao object for Activity
	@Autowired
	ActivityDao activityDao;

	
	
	//In this method we add new Activity
	@Override
	public ResponseEntity<?> addActivity(Activity activity) {
		// TODO Auto-generated method stub
		Optional<Activity> findUserById = activityDao.findById(activity.getPnrNumber());
		try {
			if (!findUserById.isPresent()) {
				int pnrNumber = new Random().nextInt(52, 1000);
				int uinNumber = new Random().nextInt(1, 800);

				activity.setPnrNumber(pnrNumber);
				activity.setActivityUIN(uinNumber);

				activityDao.save(activity);
				return new ResponseEntity<>(activity, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"Activity with UIN: " + activity.getActivityUIN() + " already exists!!");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

	//In this retrieve all the Activities
	@Override
	public Iterable<Activity> displayAllActivitys() {
		// TODO Auto-generated method stub
		return activityDao.findAll();
	}
	
	

	//In this method we update the Activity via Id 
	@Override
	public Activity updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		Optional<Activity> findUserById = activityDao.findById(activity.getActivityUIN());
		if (findUserById.isPresent()) {
			activityDao.save(activity);
		} else
			throw new RecordNotFoundException("Activity with UIN: " + activity.getActivityUIN() + " not exists!!");
		return activity;
	}
	
	
	

	//In this method we check data Existence
	@Override
	public boolean checkDataExistence(Integer activity) {
		// TODO Auto-generated method stub
		Optional<Activity> pass = activityDao.findById(activity);
		if (pass.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	//In this method we find any activity by Id
	@Override
	public ResponseEntity<?> findActivityById(Integer actId) {
		// TODO Auto-generated method stub
		Optional<Activity> findById = activityDao.findById(actId);
		try {
			if (findById.isPresent()) {
				Activity findActivity = findById.get();
				return new ResponseEntity<Activity>(findActivity, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + actId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
	
	//In this method we delete any Activity by Id
	@Override
	public String deleteActivityById(Integer actId) {
		// TODO Auto-generated method stub
		Optional<Activity> findActivityById = activityDao.findById(actId);
		if (findActivityById.isPresent()) {
			activityDao.deleteById(actId);
			return "Activity Deleted!!";
		} else
			throw new RecordNotFoundException("Activity not found for the entered UserID");
	}

	
	
	//In this method we retrieve all the activities
	@Override
	public List<Activity> addAllActivitys(List<Activity> activityList) {
		// TODO Auto-generated method stub
		Iterator<Activity> itr = activityList.iterator();
		List<Activity> resultList = new ArrayList<>();
		while (itr.hasNext()) {
			Activity pass = itr.next();

			int actNumber = new Random().nextInt(50000, 100000);
			int uinNumber = new Random().nextInt(1000, 80000);

			pass.setPnrNumber(actNumber);
			pass.setActivityUIN(uinNumber);
			pass.setActivityName(pass.getActivityName());	activityDao.save(pass);
			resultList.add(pass);
		}
		return resultList;
	}
	
	
	
}
