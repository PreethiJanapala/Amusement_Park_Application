package com.capg.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")

public class Customer {

	@Id
	private Integer pnrNumber=Integer.MIN_VALUE;
	private Integer customerUIN=Integer.MIN_VALUE;
	private String customerName;
	private int customerAge;
    private int noOfActivitys;
	
	

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Activity> activity = new ArrayList<>();
	
	
	public List<Activity> getActivityList() {
		return activity;
	}

	public void setActivityList(List<Activity> activity) {
		this.activity = activity;
	}
	
	public int getNoOfActivitys() {
		return noOfActivitys;
	}

	public void setNoOfActivitys(int noOfActivitys) {
		this.noOfActivitys = noOfActivitys;
	}
	 
}
