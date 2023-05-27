<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.study.board.BoardDTO" %>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<title>JSP 게시판</title>
</head>
<body>

<%
	int bbsNo = Integer.parseInt(request.getParameter("bbsNo"));
	String bbsPassword = request.getParameter("bbsPassword");
	BoardDAO bbsDAO = new BoardDAO();
	if(bbsPassword == ""){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호를 입력하세요')");
		script.println("history.back()");
		script.println("</script>");
	}else {
		BoardDTO checkPassword = new BoardDAO().checkPassword(bbsNo);
		String dbPassword = checkPassword.getBbsPassword();
		System.out.println("checkPassword : " + checkPassword.getBbsPassword());
		if (!dbPassword.equals(bbsPassword)) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 일치하지 않습니다.')");
			script.println("history.back()");
			script.println("</script>");

		} else {
			int result = bbsDAO.delete(bbsNo);

			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글삭제에 실패했습니다')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글삭제에 성공했습니다')");
				script.println("location.href ='main.jsp'");
				script.println("</script>");
			}
		}
	}
%>

</body>
</html>