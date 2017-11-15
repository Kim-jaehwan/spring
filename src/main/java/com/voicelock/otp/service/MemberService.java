package com.voicelock.otp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voicelcok.otp.dto.Member;
import com.voicelock.otp.dao.MemberDAO;

@Service
public class MemberService {
	
	private MemberDAO memberDAO;
	
	@Autowired
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public int insertMember(Member member) {
		return memberDAO.insertMember(member);
	}
	
	public String login(String memberId, String memberPw) {
		return memberDAO.login(memberId, memberPw);
	}

}
