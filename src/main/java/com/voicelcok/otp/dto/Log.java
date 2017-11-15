package com.voicelcok.otp.dto;

public class Log {
	private String doorlockNumIndex;
	private String memberId;
	private String logDate;
	private String logControl;
	private String doorlockNum;
	
	public Log() {
		super();
	}

	public Log(String doorlockNumIndex, String memberId, String logDate, String logControl, String doorlockNum) {
		super();
		this.doorlockNumIndex = doorlockNumIndex;
		this.memberId = memberId;
		this.logDate = logDate;
		this.logControl = logControl;
		this.doorlockNum = doorlockNum;
	}

	public String getDoorlockNumIndex() {
		return doorlockNumIndex;
	}

	public void setDoorlockNumIndex(String doorlockNumIndex) {
		this.doorlockNumIndex = doorlockNumIndex;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getLogControl() {
		return logControl;
	}

	public void setLogControl(String logControl) {
		this.logControl = logControl;
	}

	public String getDoorlockNum() {
		return doorlockNum;
	}

	public void setDoorlockNum(String doorlockNum) {
		this.doorlockNum = doorlockNum;
	}

	@Override
	public String toString() {
		return "Log [doorlockNumIndex=" + doorlockNumIndex + ", memberId=" + memberId + ", logDate=" + logDate
				+ ", logControl=" + logControl + ", doorlockNum=" + doorlockNum + "]";
	}

}
