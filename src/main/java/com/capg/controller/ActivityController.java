package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Activity;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.services.ActivityService;

@RestController
@RequestMapping("/api/v4")
public class ActivityController {


	@Autowired(required = true)
	ActivityService activityService;
	
	
	//post mapping for creating new Activity
	@PostMapping("/activity")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public ResponseEntity<?> createActivity(@RequestBody Activity activity){
		
		    activityService.addActivity(activity);
			String msg = "Activity data registered";
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
	} 
	
	
	//Get mapping for retrieving All the Activities 
	@GetMapping("/activity")
	public Iterable<Activity> viewAllActivity() {
		return activityService.displayAllActivitys();
	}
	
	
	//Get mapping for getting Activities data by Id
	@GetMapping("/activity/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchActivityById(@PathVariable("id") Integer id) {

		return activityService.findActivityById(id);
	}
	
	
	//Put mapping for updating the Activity
	@PutMapping("/activity")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Activity> updateActivity(@RequestBody Activity activity) {
	Activity p = activityService.updateActivity(activity);
	return new ResponseEntity<Activity>(p, HttpStatus.ACCEPTED);
    }

	//Delete mapping for deleting Activity by Id
	@DeleteMapping("/activity/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> deleteActivityById(@PathVariable("id") Integer id) {
		String msg = activityService.deleteActivityById(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
