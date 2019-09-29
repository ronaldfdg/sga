package com.ronaldfdg.bd;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseConnection {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sga?useSSL=false";
	private static final String JDBC_USER = "admin";
	private static final String JDBC_PASSWORD = "admin";
	private static Driver driver;

	public static synchronized Connection getConnection() throws SQLException {

		try {
			Class<?> jdbcDriverClass = Class.forName(JDBC_DRIVER);
			driver = (Driver) jdbcDriverClass.newInstance();
			DriverManager.registerDriver(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
