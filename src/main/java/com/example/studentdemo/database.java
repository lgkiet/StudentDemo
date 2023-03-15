package com.example.studentdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
	public static Connection connectDB(){
		try {
			Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as sysdba", "12122003");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
