package com.capg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	@Entity
	@Setter
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Table(name = "admins")
	public class Admin {
		
		private String adminType;
		
		@Id
		private Integer adminId=Integer.MIN_VALUE;
		
		private String adminName;
		private String adminPassword;
		private String adminAddress;
		private String adminPhone;
		private String adminEmail;
}
