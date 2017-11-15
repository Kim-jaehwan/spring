package com.voicelock.otp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class OtpDAO {
	
	private FactoryDAO factoryDAO;
	@Autowired
	public void setFactoryDAO(FactoryDAO factoryDAO) {
		this.factoryDAO = factoryDAO;
		}
	
	public String getForwardWord(int forwardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select forward_word_word from forward_word where forward_word_num=?";

		
		try {
			conn = factoryDAO.manager();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, forwardNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			 return rs.getString(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			factoryDAO.close(conn, pstmt);
		}
		return null;
	}
	
	public String getBackWord(int backNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select back_word_word from back_word where back_word_num=?";

		
		try {
			conn = factoryDAO.manager();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, backNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			 return rs.getString(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			factoryDAO.close(conn, pstmt);
		}
		return null;
	}

}
