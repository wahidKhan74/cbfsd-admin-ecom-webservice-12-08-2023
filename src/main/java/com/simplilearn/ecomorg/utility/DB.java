package com.simplilearn.ecomorg.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {

	private final String DB_URL = "jdbc:mysql://localhost:3306/ecomorg_db";
	private final String DB_USR = "devuser";
	private final String BD_PAS = "DevUser!74#";

	private Connection connection;
	private Statement statement;

	// Register the Driver
	public DB() {
		try {
			getClass().forName("com.mysql.cj.jdbc.Driver");
			System.out.println("-- JDBC Driver Loaded. --");
		} catch (Exception e) {
			System.out.println("-- Something Went Wrong: -- " + e);
		}
	}

	// Initialize DB Connection
	public void init() {
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USR, BD_PAS);
			statement = connection.createStatement();
			System.out.println("-- Connection establised --");
		} catch (Exception e) {
			System.out.println("-- Something Went Wrong: -- " + e);
		}
	}

	// Close DB Connection
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
			System.out.println("-- Connection closed --");
		} catch (Exception e) {
			System.out.println("-- Something Went Wrong: -- " + e);
		}
	}

	// Get Db Connection
	public Connection getConnecton() {
		return connection;
	}

	// select operation
	public ResultSet executeQuery(String sql) {
		ResultSet res = null;
		try {
			System.out.println("-- SQL Query --" + sql);
			res = statement.executeQuery(sql);
			System.out.println("-- Query Executed --");
		} catch (Exception e) {
			System.out.println("-- Something Went Wrong: -- " + e);
		}
		return res;
	}

	// create, update, delete operation
	public int executeUpdate(String sql) {
		int res =0;
		try {
			System.out.println("-- SQL Query --" + sql);
			res = statement.executeUpdate(sql);
			System.out.println("-- Query Executed --");
		} catch (Exception e) {
			System.out.println("-- Something Went Wrong: -- " + e);
		}
		return res;
	}

}
