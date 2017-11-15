package com.voicelock.otp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

/**
 * FactoryPattern
 * -- 공장 객체 : DB 연결객체 제공, 자원해제 제공
 * -- DAOPattern
 * - -SingletonPattern
 *
 */
public class FactoryDAO {

	private static FactoryDAO fdao;
	private static String driver;
	private static String url;
	private static String id;
	private static String pw;
	
	
	private FactoryDAO() {}
	private FactoryDAO(String driver, String url, String id, String pw) {
		this.driver = driver;
		this.url = url;
		this.id = id;
		this.pw = pw;

		try {
			Class.forName(driver);
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver Loding 오류");
		}
	}
	
	public void close(Connection conn, PreparedStatement st) {
		close(conn, st, null);
	}
	
	public void close(Connection conn, PreparedStatement st, ResultSet rs) {
		try {
			if (st!=null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static FactoryDAO getInstance() {
		return fdao;
	}
	public static FactoryDAO getInstance(String driver, String url, String id, String pw) {
		if(fdao==null) {
			fdao = new FactoryDAO(driver, url, id, pw);
		}
			return fdao;
	}

	public Connection manager(){
		try {
			return DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 오류");
		}
		return null;
	}
}
