package com.voicelock.otp.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.voicelcok.otp.dto.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class MemberDAO {
	
	private FactoryDAO factoryDAO;
	
	@Autowired
	public void setFactoryDAO(FactoryDAO factoryDAO) {
		this.factoryDAO = factoryDAO;
	}
	
	public int insertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = factoryDAO.manager();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberAddress());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getNotification());
			pstmt.setString(8, member.getMemberPublickey());
			pstmt.setString(9, member.getLineId());
			return pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factoryDAO.close(conn, pstmt);
		}
		
		return 0;
	}
	
	public String login(String memberId, String memberPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select line_id from member where member_id=? and member_pw=?";
		
		try {
			conn = factoryDAO.manager();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			return rs.getString("line_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factoryDAO.close(conn, pstmt);
		}	
		return null;
	}
}
