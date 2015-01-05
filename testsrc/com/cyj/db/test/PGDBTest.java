package com.cyj.db.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class PGDBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mmdb", "mm", "mm");
			System.out.println(conn.isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
