package com.capg.services;

import org.springframework.http.ResponseEntity;

import com.capg.entity.Admin;

public interface AdminService {

	public ResponseEntity<?> createAdmin(Admin newAdmin);

	public Admin updateAdmin(Admin newAdmin);

	public String deleteAdmin(Integer AdminId);

	public Iterable<Admin> displayAllAdmin();

	public ResponseEntity<?> findAdminById(Integer adminId);
	
	public boolean checkDataExistence(Admin newAdmin);
	
	public Admin returnAdmin(Integer adminId);
}
