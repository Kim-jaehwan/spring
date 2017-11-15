package com.voicelock.otp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LineController {
	
	@RequestMapping(value="test.do", method=RequestMethod.POST)
	@ResponseBody
	public Map process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      String result = getBody(req);
	      String[] array = result.split("\"");
	      Map<String, String> test = new HashMap<String, String>();
	      test.put("location", "가산금천구");
	      test.put("engineerName", "김재환");	
	      test.put("engineerPhone", "01012341234");
	      test.put("latitude", "34.554353");
	      test.put("longitude", "127.666435");
	      System.out.println("json 결과값===>"+result);      
	      
	      return test;
	   }
	   public static String getBody(HttpServletRequest request) throws IOException {
	      String body = null;
	      StringBuilder stringBuilder = new StringBuilder();
	      BufferedReader bufferedReader = null;

	      try {
	         InputStream inputStream = request.getInputStream();
	         if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	               stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	         }
	      } catch (IOException ex) {
	         throw ex;

	      } finally {
	         if (bufferedReader != null) {
	            try {
	               bufferedReader.close();
	            } catch (IOException ex) {
	               throw ex;
	            }
	         }
	      }

	      body = stringBuilder.toString();

	      return body;
	   }

}
