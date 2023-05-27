<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.study.board.BoardDTO" %>
<jsp:useBean id="bbs" class="com.study.board.BoardDTO" scope="page"/>
<jsp:setProperty name="bbs" property="bbsNo" />
<jsp:setProperty name="bbs" property="category" />
<jsp:setProperty name="bbs" property="bbsWriter" />
<jsp:setProperty name="bbs" property="bbsTitle" />
<jsp:setProperty name="bbs" property="bbsPassword" />
<jsp:setProperty name="bbs" property="bbsFile" />
<jsp:setProperty name="bbs" property="bbsContent" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<title>JSP 게시판</title>
</head>
<body>

<%

	String bbsWriter = request.getParameter("bbsWriter");
	String bbsTitle = request.getParameter("bbsTitle");
	String bbsPassword = request.getParameter("bbsPassword");
	String bbsFile = request.getParameter("bbsFile");
	String bbsContent = request.getParameter("bbsContent");

	int bbsNo = bbs.getBbsNo();
//	if(request.getParameter("bbsNo") != null){
//		bbsNo = Integer.parseInt(request.getParameter("bbsNo"));
//	}

	System.out.println("updateAction : "+bbsPassword);

%>

<%
		if(bbsPassword == ""){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호를 입력하세요')");
			script.println("history.back()");
			script.println("</script>");
		}else{
			BoardDTO checkPassword = new BoardDAO().checkPassword(bbsNo);
			if(checkPassword.getBbsPassword().equals(bbsPassword)){
				BoardDAO bbsDAO = new BoardDAO();
				int result = bbsDAO.update(bbsNo, bbsWriter, bbsPassword, bbsTitle, bbsContent, bbsFile);
				if (result == -1) {
					PrintWriter script = response.getWriter();
					System.out.println("result : "+result);
					System.out.println("result : "+bbs.getCategory());

					script.println("<script>");
					script.println("alert('글수정에 실패했습니다')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href ='main.jsp'");
					script.println("</script>");
				}
			}else{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호가 일치하지 않습니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
		}
%>
</body>
</html>