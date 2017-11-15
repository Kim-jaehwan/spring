<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function confirmPw(){
		if(document.getElementById("memberPw")==null){
			alert("PASSWORD를 입력하세요!");
		}
	}
	
	function confirmKey(){
		window.open('result.jsp', '결과');
	}

</script>

</head>
<body>
<!-- 암호요청 시  암호화된 memberPw를 받아 아스키코드번호로 전환 후 seed값 만들어 키 생성 -->
<%
	if(session.getAttribute("memberPw")==null) {
%>
	<p>${message} </p>
<%	
	} else {
%>
	<form action="createKey.do" method="post">
	<input type="submit" value="키요청" />
	</form>
	<P><%if(session.getAttribute("numberKey")!=null){%><%=session.getAttribute("numberKey") %> <%} %> </P>
	<P><%if(session.getAttribute("wordKey")!=null){%><%=session.getAttribute("wordKey")%><%}%> </P>
	<hr>
	<br>
	<form action="confirmKey.do" method="post">
		OTP 입력 : <input type="text" name="confirmOTP" id="confirmOTP" />
 		<input type="submit" name="confirmKey" id="confrimKey" value="확인" onclick="confirmKey()"/>
	</form>
	<p>${message}</p>
<%
	}
%>
</body>
</html>