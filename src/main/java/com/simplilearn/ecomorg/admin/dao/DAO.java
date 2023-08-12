package com.simplilearn.ecomorg.admin.dao;

import java.util.List;

public interface DAO<T> {

	// Get all records
	List<T> getAll();

	// Get One record
	T getOne(long id);

	// create a record
	int save(T obj);

	// update a record
	int update(T obj);

	// delete a record
	int delete(long id);
}
