package com.simplilearn.ecomorg.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.ecomorg.admin.model.User;
import com.simplilearn.ecomorg.utility.DB;

public class UserDao implements DAO<User>{
	
	DB db  = new DB();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//Get all users
	public List<User> getAll() {
		db.init();
		List<User> usersList = new  ArrayList<User>();
		try {
			String sql = "select * from USERS";
			ResultSet set = db.executeQuery(sql);
			while (set.next()) { 
				User user = new User();
				user.setUserId(set.getInt("userId"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setFullName(set.getString("fullName"));
				user.setStreet(set.getString("street"));
				user.setCity(set.getString("city"));
				user.setState(set.getString("state"));
				user.setCountry(set.getString("country"));
				user.setPincode(set.getInt("pincode"));
				user.setImage(set.getString("image"));
				user.setContact(set.getLong("contact"));
				user.setAddedOn(format.parse(set.getString("addedOn")));
				usersList.add(user);
			}
			
		} catch (Exception e) {
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return usersList;
	}

	public User getOne(long id) {
		db.init();
		User  user = new  User();
		try {
			String sql = "select * from USERS where userId="+id;
			ResultSet set = db.executeQuery(sql);
			if (set.next()) { 
				user.setUserId(set.getInt("userId"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setFullName(set.getString("fullName"));
				user.setStreet(set.getString("street"));
				user.setCity(set.getString("city"));
				user.setState(set.getString("state"));
				user.setCountry(set.getString("country"));
				user.setPincode(set.getInt("pincode"));
				user.setImage(set.getString("image"));
				user.setContact(set.getLong("contact"));
				user.setAddedOn(format.parse(set.getString("addedOn")));
			}
			
		} catch (Exception e) {
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return user;
	}

	public int save(User obj) {
		db.init();
		int rowsAffected = 0;
		try {
			String addedOnDate = format.format(obj.getAddedOn());
			String sql = String.format(
				"insert into USERS values(null, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, '%s', %d, '%s')",
				obj.getEmail(),obj.getPassword(),obj.getFullName(), obj.getStreet(),obj.getCity(),obj.getState(),
				obj.getCountry(), obj.getPincode(), obj.getImage(),obj.getContact(),addedOnDate);
			rowsAffected =db.executeUpdate(sql);
			String message = (rowsAffected>0) ? "User created successfully" : "Failed to save user.";
			System.out.println(message);
			return rowsAffected;
		} catch (Exception e) {
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return rowsAffected;
	}

	public int update(User obj) {
		db.init();
		int rowsAffected = 0;
		try { 
			String addedOnDate = format.format(obj.getAddedOn());
			String sql = String.format(
					"update USERS set email='%s', password='%s', fullName='%s', street='%s', city='%s', state='%s', country='%s', "
					+ "pincode='%s', image='%s', contact='%d', addedOn='%s' where userId=%d",
					obj.getEmail(), obj.getPassword(), obj.getFullName(), obj.getStreet(), obj.getCity(),
					obj.getState(), obj.getCountry(), obj.getPincode(), obj.getImage(), obj.getContact(), addedOnDate,
					obj.getUserId()

			);
			rowsAffected =db.executeUpdate(sql);
			String message = (rowsAffected>0) ? "User updated successfully" : "Failed to update user.";
			System.out.println(message);
			return rowsAffected;
		} catch (Exception e) {
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return rowsAffected;
	}

	public int delete(long id) {
		db.init();
		int rowsAffected = 0;
		try { 
			String sql = "delete from USERS where userId = " + id;
			rowsAffected =db.executeUpdate(sql);
			String message = (rowsAffected>0) ? "User deleted successfully" : "Failed to delete user.";
			System.out.println(message);
			return rowsAffected;
		}catch (Exception e) {
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return rowsAffected;
	}

}
