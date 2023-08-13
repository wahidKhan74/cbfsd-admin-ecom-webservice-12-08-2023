package com.simplilearn.ecomorg.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.ecomorg.admin.model.Admin;
import com.simplilearn.ecomorg.utility.DB;

public class AdminDao implements DAO<Admin>{

	DB db = new DB();
	
	// validate admin login
	public Admin login(Admin admin) {
		db.init();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String sql = "select * from ADMINS where email='"+admin.getEmail()
			+"' and password='"+admin.getPassword() +"'";
			ResultSet res = db.executeQuery(sql);
			if (res.next()) {
				admin.setAdminId(res.getInt("adminId"));
				admin.setFullName(res.getString("fullName"));
				admin.setEmail(res.getString("email"));
				admin.setPassword(res.getString("password"));
				admin.setLoginType(res.getInt("loginType"));
				admin.setAddedOn(format.parse(res.getString("addedOn")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return admin;
	}
	
	// Get all admin users
	public List<Admin> getAll() {
		db.init();
		List<Admin> adminList = new ArrayList<Admin>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String sql = "select * from ADMINS";
			ResultSet res = db.executeQuery(sql);
			while (res.next()) {
				Admin admin = new Admin();
				admin.setAdminId(res.getInt("adminId"));
				admin.setFullName(res.getString("fullName"));
				admin.setEmail(res.getString("email"));
				admin.setPassword(res.getString("password"));
				admin.setLoginType(res.getInt("loginType"));
				admin.setAddedOn(format.parse(res.getString("addedOn")));
				adminList.add(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return adminList;
	}

	public Admin getOne(long id) {
		Admin admin = new Admin();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		db.init();
		try {
			String sql = "select * from ADMINS where adminId="+id;
			ResultSet res = db.executeQuery(sql);
			if (res.next()) {
				admin.setAdminId(res.getInt("adminId"));
				admin.setFullName(res.getString("fullName"));
				admin.setEmail(res.getString("email"));
				admin.setPassword(res.getString("password"));
				admin.setLoginType(res.getInt("loginType"));
				admin.setAddedOn(format.parse(res.getString("addedOn")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return admin;
	}

	// create an admin
	public int save(Admin obj) {
		db.init();
		try {
			String sql = "insert into ADMINS(email, password,fullName,loginType) values('"+
					obj.getEmail()+"', '"+obj.getPassword()+"', '"+obj.getFullName()+"', "+
					obj.getLoginType()+")";
			int rowAffected = db.executeUpdate(sql);
			String message = (rowAffected>0) ? "Admin user created successfully" : "Unable to save Admin user.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
	}

	public int update(Admin obj) {
		db.init();
		try {
			String sql = "update ADMINS set email = '"+obj.getEmail()+"', password ='"+obj.getPassword()+"', fullName = '"+obj.getFullName()
			+"', loginType = "+obj.getLoginType()+" where adminId = "+obj.getAdminId();
			int rowAffected = db.executeUpdate(sql);
			String message = (rowAffected >0 ) ? "Admin record updated successfully" : "Unable to update Admin data.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
	}

	public int delete(long id) {
		db.init();
		try {
			String sql = "delete from ADMINS where adminId = " + id;
			int rowAffected = db.executeUpdate(sql);
			String message = (rowAffected >0 ) ? "Admin record deleted successfully" : "Unable to delete Admin data.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
	}
	
	
	
}
