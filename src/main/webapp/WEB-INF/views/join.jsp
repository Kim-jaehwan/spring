<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>회원가입</h3>

<form method="post" action="join.do">
	아이디 : <input type="text" id="memberId" name="memberId" />
	비밀번호 : <input type="password" id="memberPw" name="memberPw" />
	이름 : <input type="text" id="memberName" name="memberName" />
	연락처 : <input type="text" id="memberPhone" name="memberPhone" />
	주소 : <input type="text" id="memberAddress" name="memberAddress" />
	이메일 : <input type="text" id="memberEmail" name="memberEmail" />
	<input type="submit" id="join" name="join" value="회원가입" />
</form>

</body>
</html>