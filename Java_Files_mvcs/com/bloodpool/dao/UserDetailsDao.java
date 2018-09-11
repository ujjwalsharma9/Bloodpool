package com.bloodpool.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bloodpool.entity.UserDetailsEntity;
import com.bloodpool.service.LoginService;

import static com.bloodpool.service.OfyService.ofy;

@SuppressWarnings("unused")
public class UserDetailsDao {
	static UserDetailsEntity ud = null;

	// Register
	// Add a User
	public void save(UserDetailsEntity ude) {

		ofy().save().entity(ude);
		ofy().clear();
		System.out.println("Data Saved");

	}

	// Check Existing User
	public boolean check(String uID) {
		UserDetailsEntity det = ofy().load().type(UserDetailsEntity.class).id(uID).now();

		if (det != null)
			return true;
		else
			return false;
	}

	public String getFirstName(String emailID) {
		UserDetailsEntity userDetailsEntity = ofy().load().type(UserDetailsEntity.class).id(emailID).now();
		return userDetailsEntity.getFirstName();
	}

	public List<UserDetailsEntity> getDonors() {
		List<UserDetailsEntity> userDetailsEntities = ofy().load().type(UserDetailsEntity.class).list();
		return userDetailsEntities;
	}

	public void setNotifications(int i, String patientID, ArrayList<UserDetailsEntity> bloodDonorList) {

		UserDetailsEntity uDe = bloodDonorList.get(i);

		uDe.setRequests(patientID);

		ofy().save().entity(uDe);
		ofy().clear();

	}

	public boolean authenticateUser(LoginService loginService) {

		String emailID = loginService.getEmailID();
		String pass = loginService.getPass();

		UserDetailsEntity userDetailsEntity = ofy().load().type(UserDetailsEntity.class).id(emailID).now();

		if (userDetailsEntity != null) {
			if (userDetailsEntity.getPass().equals(pass)) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void updatePassword(String emailID, String password) {

		UserDetailsEntity up = ofy().load().type(UserDetailsEntity.class).id(emailID).now();
		ofy().delete().type(UserDetailsEntity.class).id(emailID).now();
		up.setPass(password);
		save(up);
	}

}