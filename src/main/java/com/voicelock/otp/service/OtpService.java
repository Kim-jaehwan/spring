package com.voicelock.otp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voicelock.otp.dao.OtpDAO;

@Service
public class OtpService {
	OtpDAO otpDAO;
	@Autowired
	public void setOtpDAO(OtpDAO otpDAO) {
		this.otpDAO = otpDAO;
	}
	
	public String getForwardWord(int forwardNum) {
		return otpDAO.getForwardWord(forwardNum);
	}
	
	public String getBackWord(int backNum) {
		return otpDAO.getBackWord(backNum);
	}
	

}
