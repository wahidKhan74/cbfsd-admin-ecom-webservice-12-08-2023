package com.simplilearn.ecomorg.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.ecomorg.admin.model.Categories;
import com.simplilearn.ecomorg.utility.DB;

public class CategoriesDAO  implements DAO<Categories>{

	DB db  = new DB();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// Get all Categories
	public List<Categories> getAll() {
		db.init();
		List<Categories> categoriesList = new ArrayList<Categories>();
		try { 
			String sql = "select * from CATEGORIES";
			ResultSet set = db.executeQuery(sql);
			while(set.next()) { 
				Categories category = new Categories();
				//set or map result set to object
				category.setCategoryId(set.getInt("categoryId"));
				category.setCategoryName(set.getString("categoryName"));
				category.setCategoryDescription(set.getString("categoryDescription"));
				category.setCategoryImageUrl(set.getString("categoryImageUrl"));
				category.setActive(set.getInt("active"));
				category.setAddedOn(format.parse(set.getString("addedOn")));
				//add category into categoriesList
				categoriesList.add(category);
			}
		}catch (Exception e) {
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return categoriesList;
	}

	public Categories getOne(long id) {
		db.init();
		Categories category = new Categories();
		try {
			String sql = "select * from CATEGORIES where categoryId = " + id;
			ResultSet set = db.executeQuery(sql);
			if(set.next()) { 
				//set or map result set to object
				category.setCategoryId(set.getInt("categoryId"));
				category.setCategoryName(set.getString("categoryName"));
				category.setCategoryDescription(set.getString("categoryDescription"));
				category.setCategoryImageUrl(set.getString("categoryImageUrl"));
				category.setActive(set.getInt("active"));
				category.setAddedOn(format.parse(set.getString("addedOn")));
			}
		}catch (Exception e) {
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}	
		return category;
	}

	public int save(Categories obj) {
		db.init();
		int rowsAffected = 0;
		try { 
			String addedOnDate = format.format(obj.getAddedOn());
			String sql = "insert into CATEGORIES(categoryName,categoryDescription,categoryImageUrl,active,addedOn) "
					+ "values('" + obj.getCategoryName() + "', '"+ obj.getCategoryDescription() 
					+ "', '" + obj.getCategoryImageUrl() + "', " + obj.getActive()
					+ " , '" + addedOnDate + "')";
			rowsAffected = db.executeUpdate(sql);
			String message = (rowsAffected > 0) ? "Category created successfully" : "Failed to create category";
			System.out.println(message);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return rowsAffected;
	}

	public int update(Categories obj) {
		db.init();
		int rowsAffected = 0;
		try { 
			String addedOnDate = format.format(obj.getAddedOn());
			String sql = "update CATEGORIES set categoryName = '" + obj.getCategoryName() + "', categoryDescription = '"
					+ obj.getCategoryDescription() + "', categoryImageUrl = '" + obj.getCategoryImageUrl()
					+ "', active = " + obj.getActive() + " , addedOn = '" + addedOnDate + "' where categoryId = "
					+ obj.getCategoryId();
			rowsAffected = db.executeUpdate(sql);
			String message = (rowsAffected > 0) ? "Category updated successfully" : "Failed to update category";
			System.out.println(message);
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
			String sql = "delete from CATEGORIES where categoryId = " + id;
			rowsAffected = db.executeUpdate(sql);
			String message = (rowsAffected > 0) ? "Category id deleted" : "Category cannot be deleted";
			System.out.println(message);
		}catch (Exception e) {
			System.out.println("Something went wrong :: " + e.getMessage());
		} finally {
			db.destroy();
		}
		return rowsAffected;
	}

}
