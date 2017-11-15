package com.voicelock.otp;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.voicelock.otp.service.OtpService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class OTPController {
	static final int responseTime = 120000;

	Timer destroyTimer;
	TimerTask destroyTask;
	
	
	private OtpService otpService;
	@Autowired
	public void setOtpServcie(OtpService otpService) {
		this.otpService = otpService;
	}
	
	
	private static final Logger logger = LoggerFactory.getLogger(OTPController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
/*	@RequestMapping(value = "home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}*/
	
	
	@RequestMapping(value="createKey.do")
	public ModelAndView createKey(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		int controllNum = 1;
		session.setAttribute("controllNum", controllNum);
		
		String memberPw = (String) session.getAttribute("memberPw");

		String personalPassword = memberPw;
/*		String personalPassword = "?낛淹?&??F:?#O펯&?\\褥?뢗R]? 댬 go0a\n" + 
				"?:쭮?}H?A      +? mXf?\"?-?8.\n" + 
				"墻 ?< 횉V ?\\rx)т +   켩뻠_; ?J?&篤 ?뷫컹?곺 ?CUe 퀄i?B煉?$t\n" + 
				"?-";
*/
		if(personalPassword==null || personalPassword.length() <= 0) {
			String message = "비밀번호 미입력";
			mv.addObject("message", message);
			mv.setViewName("check");
			return mv;
		}
		
		else {

		String personalSeed="";
		char ch[] = personalPassword.toCharArray();
		for(int i=0; i<ch.length; i++) {
			if(personalSeed.length()<6) {
		 personalSeed += ((int) ch[i]);
			}
		}
		System.out.println("string : " + personalSeed);
		long seed = Long.parseLong(personalSeed);
		System.out.println("long : " + seed);
		Random ran = new Random();
		Random ran2 = new Random();
		System.out.println("--- seed 설정 전(시간만 설정) ---");
		ran.setSeed(System.currentTimeMillis());
		ran2.setSeed(System.currentTimeMillis());
		System.out.println("랜1 : " + ran.nextInt());
		System.out.println("랜2 : " + ran2.nextInt());
		System.out.println("--------------------");
		ran.setSeed(seed+System.currentTimeMillis());
		ran2.setSeed(System.currentTimeMillis());
		System.out.println("--- seed 설정 ---");
		System.out.println("랜 :" + ran.nextInt());
		System.out.println("랜2 :" + ran2.nextInt());
		String numberKey = Integer.toString(Math.abs(ran.nextInt())).substring(1,7);
		System.out.println("숫자키 : "+ numberKey);
		int forwardNum = Integer.parseInt(numberKey.substring(0,3));
		int backNum = Integer.parseInt(numberKey.substring(3,6));
		System.out.println("앞단어숫자 : " + forwardNum);
		System.out.println("뒷단어숫자 : " + backNum);
		String forwardWord = otpService.getForwardWord(forwardNum);
		String backWord = otpService.getBackWord(backNum);
		String wordKey = forwardWord+backWord;
		System.out.println("단어 키 : "+ wordKey);

		session.setAttribute("numberKey", numberKey);
		session.setAttribute("wordKey", wordKey);
		
		mv.addObject("numberKey", numberKey);
		mv.addObject("wordKey", wordKey);
		mv.setViewName("check");
		
		destroyTimer = new Timer();
		destroyTask = new TimerTask() {
			
			@Override
			public void run() {
				session.removeAttribute("numberKey");
				session.removeAttribute("wordKey");	
				System.out.println("키삭제");
			}
		};
		
		destroyTimer.schedule(destroyTask, responseTime);
		
		return mv;
		}
	}
	
	@RequestMapping("confirmKey.do")
	public ModelAndView confirmKey(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		sendMsg(session);
		String confirmOTP = (String) session.getAttribute("inMsg");
		String message;
		String wordKey = (String)session.getAttribute("wordKey");
		//String numberKey = (String)session.getAttribute("numberKey");
		System.out.println(wordKey);
		System.out.println(confirmOTP);
		if(confirmOTP!=null || confirmOTP.length() > 0) {
		if(wordKey.equals(confirmOTP)) {
			message ="인증성공!!";
			System.out.println("인증성공!!");
			mv.addObject("message", message);
			mv.setViewName("result"); // 나중에 check로 (테스트
			session.removeAttribute("isMsg");
			//destroyTimer.cancel();
			//destroyTask.cancel();
			destroyTimer = new Timer();
			destroyTask = new TimerTask() {
				@Override
				public void run() {
					session.removeAttribute("wordKey");
					session.removeAttribute("numberKey");
					System.out.println("키삭제");
				}
			};
			destroyTimer.schedule(destroyTask, 0);
			return mv;
		} else {
			message = "인증실패!!";
			System.out.println("인증실패!!"); 
			mv.addObject("message", message);
			mv.setViewName("result"); // 나중에 check로
			return mv;	
		}
	} else
		message = "OTP암호를 입력하세요!";
		mv.addObject("message", message);
		mv.setViewName("check");
		return mv;
	}
	
	@RequestMapping("checkView.do")
	public String checkView() {
		return "check";
	}
	
	//////////////////////////////////
	//@RequestMapping("serverStart.do")
	public void sendMsg(HttpSession session){
		ServerSocket serverSocket = null;
		int controll = (int)session.getAttribute("controllNum");

		try {
			//서버소켓생성
			serverSocket = new ServerSocket(5000); 
			System.out.println("======대기중======");
			
			//클라이언트에서 받아올 소켓
			Socket socket = serverSocket.accept();
			System.out.println("ip주소 "+socket.getInetAddress().getHostAddress()+"가 접속하였습니다.");
			
			//출력스트림 
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			//입력스트림
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inMsg = in.readLine();
			
			// server => client 보내기
			//out.println("open the door!!");
			out.println(controll);

			// server => client 가져오기 			
			session.setAttribute("inMsg", inMsg);
			System.out.println("클라이언트 : "+inMsg);
		
			out.close();
			in.close();
			socket.close();
			System.out.println(">> 소켓종료");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("서버연결 실패");
		}finally {
			
		}
	}
}

