package com.simplilearn.ecomorg.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.simplilearn.ecomorg.admin.dao.AdminDao;
import com.simplilearn.ecomorg.admin.model.Admin;
import com.simplilearn.ecomorg.admin.model.Response;

@WebServlet("/admins/login")
public class AdminAuthController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	AdminDao adminDao = new AdminDao();
	Response responseDto = new Response();

	public AdminAuthController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// map admin object fields with request parameters
		Admin admin = new Admin();
		admin.setEmail(request.getParameter("email"));
		admin.setPassword(request.getParameter("password"));
		try {
			Admin fetchedAdmin = adminDao.login(admin);
			if (fetchedAdmin.getAdminId() > 0) {
				responseDto.setStatus("200 OK");
				responseDto.setMessage("Admin user login successfull.");
			} else {
				responseDto.setStatus("400");
				responseDto.setMessage("Failed to login Admin user.");
			}
		} catch (Exception e) {
			responseDto.setStatus("400");
			responseDto.setMessage("Failed to login Admin user.");
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
