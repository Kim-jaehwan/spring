package com.voicelock.otp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voicelcok.otp.dto.Member;
import com.voicelock.otp.service.MemberService;

@Controller
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	@RequestMapping("join.do")
	public String addMember(Member member) {

		member.setMemberPublickey("xxxx");
		member.setNotification("ON");

		if(member.getLineId() == null || member.getLineId().length() <= 0 ) {
			member.setLineId("X");
		}
		
		if(member.getMemberId() == null || member.getMemberId().length() < 5 || member.getMemberId().length() > 20 || member.getMemberPw() == null || member.getMemberPw().length() < 6 || member.getMemberPw().length() > 16) {
			return "join"; // 회원가입 실패
		}
		else {
			int result = memberService.insertMember(member);
			if(result==1) {
				return "index"; // 회원가입 성공				
			}else {
				return "join";
			}
			
		}
	}
	/**
	 * 로그인
	 * @param memberId
	 * @param memberPw
	 * @param session
	 * @return
	 */
	@RequestMapping("login.do")
	public String login(String memberId, String memberPw, HttpSession session) {
		
		if(memberId == null || memberId.length() <= 0 || memberPw == null || memberPw.length() <= 0) {
			return "user/login";
		} else {
			String result = memberService.login(memberId, memberPw);
			if(result == null) {
				System.out.println("로그인실패");
				return "user/login";
			} else {				
				session.setAttribute("memberPw", memberPw);
				return "check";
			}
		}
	}
}
