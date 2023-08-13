package com.simplilearn.ecomorg.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.simplilearn.ecomorg.admin.dao.UserDao;
import com.simplilearn.ecomorg.admin.model.Response;
import com.simplilearn.ecomorg.admin.model.User;

// Users controller for all user related crud operations
@WebServlet("/admins/users")
public class UsersController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserDao userDao = new UserDao();
	Response responseDto = new Response();

	public UsersController() {
		super();
	}

	// get one or list all users
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		List<User> data = new ArrayList<User>();

		if (id != null && Integer.parseInt(id) != 0) {
			data.add(userDao.getOne(Integer.parseInt(id)));
		} else {
			data = userDao.getAll();
		}
		mapResponse(response, data);
	}

	// create user
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// create user mapping
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setFullName(request.getParameter("fullName"));
		user.setStreet(request.getParameter("street"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setCountry(request.getParameter("country"));
		user.setPincode(Integer.parseInt(request.getParameter("pincode")));
		user.setImage(request.getParameter("image"));
		user.setContact(Long.parseLong(request.getParameter("contact")));
		user.setAddedOn(new Date());

		try {
			userDao.save(user);
			responseDto.setStatus("200 OK");
			responseDto.setMessage("User created successfully");
			responseDto.setData(user);
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to create admin user");
		}
		mapResponse(response, responseDto);
	}

	// update user
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// create user mapping
		User user = new User();
		user.setUserId(Integer.parseInt(request.getParameter("userId")));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setFullName(request.getParameter("fullName"));
		user.setStreet(request.getParameter("street"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setCountry(request.getParameter("country"));
		user.setPincode(Integer.parseInt(request.getParameter("pincode")));
		user.setImage(request.getParameter("image"));
		user.setContact(Long.parseLong(request.getParameter("contact")));
		user.setAddedOn(new Date());

		try {
			userDao.update(user);
			responseDto.setStatus("200 OK");
			responseDto.setMessage("User updated successfully");
			responseDto.setData(user);
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to update user");
		}
		mapResponse(response, responseDto);
	}

	// delete user
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("id"));
		try {
			userDao.delete(userId);
			responseDto.setStatus("200 OK");
			responseDto.setMessage("User deleted successfully");
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to delete user");
		}
		mapResponse(response, responseDto);
	}

	private void mapResponse(HttpServletResponse response, Object data) throws ServletException, IOException {
		// set response as json
		String jsonResponse = new Gson().toJson(data);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}
}
