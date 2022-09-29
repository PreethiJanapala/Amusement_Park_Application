package com.capg.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity")
public class Activity {

	
	@Id
    private Integer pnrNumber=Integer.MIN_VALUE;
	private Integer activityUIN=Integer.MIN_VALUE;
	private String activityName;
	
		
}
