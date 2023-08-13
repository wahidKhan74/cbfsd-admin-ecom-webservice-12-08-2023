package com.simplilearn.ecomorg.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.simplilearn.ecomorg.admin.dao.CategoriesDAO;
import com.simplilearn.ecomorg.admin.model.Categories;
import com.simplilearn.ecomorg.admin.model.Response;

@WebServlet("/admins/categories")
public class CategoriesController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CategoriesDAO categoriesDAO = new CategoriesDAO();
	Response responseDto = new Response();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// Get one or all categories
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		
		String id = request.getParameter("id");
		List<Categories> data = new ArrayList<Categories>();

		if (id != null && Integer.parseInt(id) != 0) {
			data.add(categoriesDAO.getOne(Integer.parseInt(id)));
		} else {
			data = categoriesDAO.getAll();
		}
		mapResponse(response, data);
	}

	// create category
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		try { 
			// Fields Mapping
			Categories category = new Categories();
			category.setCategoryName(request.getParameter("categoryName"));
			category.setCategoryDescription(request.getParameter("categoryDescription"));
			category.setActive(Integer.parseInt(request.getParameter("active")));
			category.setCategoryImageUrl(request.getParameter("catgeoryImageUrl"));
			category.setAddedOn(new Date());
			
			//save category
			categoriesDAO.save(category);
			responseDto.setStatus("Success");
			responseDto.setMessage("Categrory is created successfully!");
			
		} catch (Exception e) {
			e.printStackTrace();
			responseDto.setMessage("Failed create categrory data");
			responseDto.setStatus("Failed");
		}
		mapResponse(response, responseDto);
	}

	// update category
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		try { 
			// Fields Mapping
			Categories category = new Categories();
			category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			category.setCategoryName(request.getParameter("categoryName"));
			category.setCategoryDescription(request.getParameter("categoryDescription"));
			category.setActive(Integer.parseInt(request.getParameter("active")));
			category.setCategoryImageUrl(request.getParameter("catgeoryImageUrl"));
			category.setAddedOn(new Date());
			
			//save category
			categoriesDAO.update(category);
			responseDto.setStatus("Success");
			responseDto.setMessage("Categrory is updated successfully!");
			
		} catch (Exception e) {
			responseDto.setMessage("Failed update categrory data");
			responseDto.setStatus("Failed");
		}
		mapResponse(response, responseDto);
	}
	
	// delete category
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		String id = request.getParameter("id");
		try { 
			int rowAffected = categoriesDAO.delete(Integer.parseInt(id));
			if(rowAffected > 0) { 
				responseDto.setStatus("Success");
				responseDto.setMessage("Category is deleted successfully!");
			} else  {
				responseDto.setStatus("Failed");
				responseDto.setMessage("Category does not exist with id "+id);
			}
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Category does not exist with id "+id);
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
