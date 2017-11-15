package com.voicelock.otp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping("indexView.do")
	public String indexView() {
		return "index";
	}
	
	@RequestMapping("joinView.do")
	public String joinView() {
		return "join";
	}
	
	@RequestMapping(value = "loginView.do")
	public String loginView() {
			return "user/login";
	}

}
