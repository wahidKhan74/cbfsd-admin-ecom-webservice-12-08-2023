package com.simplilearn.ecomorg.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.simplilearn.ecomorg.admin.dao.AdminDao;
import com.simplilearn.ecomorg.admin.model.Admin;
import com.simplilearn.ecomorg.admin.model.Response;

@WebServlet("/admins")
public class AdminController extends HttpServlet {

	AdminDao adminDao = new AdminDao();
	Response responseDto = new Response();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		List<Admin> adminList = new ArrayList<Admin>();
		if (id != null && Integer.parseInt(id) != 0) {
			Admin admin = adminDao.getOne(Integer.parseInt(id));
			adminList.add(admin);
		} else {
			adminList = adminDao.getAll();
		}
		// set response as json
		String jsonResponse = new Gson().toJson(adminList);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}

	// create an admin user
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Admin admin = new Admin();
			admin.setEmail(request.getParameter("email"));
			admin.setPassword(request.getParameter("password"));
			admin.setFullName(request.getParameter("fullName"));
			admin.setLoginType(Integer.parseInt(request.getParameter("loginType")));
			// save admin user into
			adminDao.save(admin);
			responseDto.setStatus("200 OK");
			responseDto.setMessage("Admin user created successfully");
			responseDto.setData(admin);
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to create admin user");
		}
		// set response as json
		String jsonResponse = new Gson().toJson(responseDto);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}

	// update an admin user
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Admin admin = new Admin();
			admin.setAdminId((Integer.parseInt(request.getParameter("adminId"))));
			admin.setEmail(request.getParameter("email"));
			admin.setPassword(request.getParameter("password"));
			admin.setFullName(request.getParameter("fullName"));
			admin.setLoginType(Integer.parseInt(request.getParameter("loginType")));
			// update admin user into
			adminDao.update(admin);
			responseDto.setStatus("200 OK");
			responseDto.setMessage("Admin user update successfully");
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to upadte admin user");
		}
		// set response as json
		String jsonResponse = new Gson().toJson(responseDto);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}

	// delete an admin user
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String id = request.getParameter("id");
			
			// delete admin user into
			adminDao.delete(Integer.parseInt(id));
			responseDto.setStatus("200 OK");
			responseDto.setMessage("Admin user deleted successfully");
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to deleted admin user");
		}
		// set response as json
		String jsonResponse = new Gson().toJson(responseDto);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}
}
