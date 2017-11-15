package com.voicelcok.otp.dto;

public class Doorlock {
	private String doorlockNum;
	private String memberId;
	
	public Doorlock() {
		super();
	}
	
	public Doorlock(String doorlockNum, String memberId) {
		super();
		this.doorlockNum = doorlockNum;
		this.memberId = memberId;
	}

	public String getDoorlockNum() {
		return doorlockNum;
	}

	public void setDoorlockNum(String doorlockNum) {
		this.doorlockNum = doorlockNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Doorlock [doorlockNum=" + doorlockNum + ", memberId=" + memberId + "]";
	}

}
