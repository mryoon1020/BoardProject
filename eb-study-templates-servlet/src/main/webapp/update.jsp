<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="com.study.board.BoardDTO"%>
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
		int bbsNo = 0;
		if(request.getParameter("bbsNo") != null){
			bbsNo = Integer.parseInt(request.getParameter("bbsNo"));
			System.out.println("updatePage : "+bbsNo);
		}
		BoardDTO bbs = new BoardDAO().bbsRead(bbsNo);
	%>
<form method="post" action="updateAction.jsp">
<div>
	<div>
		<input type="hidden" name="bbsNo" value="<%= bbs.getBbsNo()%>">
		<div>카테고리</div>
		<div><%= bbs.getCategory()%></div>
	</div>
	<div>
		<div>등록일시</div>
		<div><%= bbs.getBbsWDate()%></div>
	</div>
	<div>
		<div>수정일시</div>
		<div><%= bbs.getBbsUDate()%></div>
	</div>
	<div>
		<div>작성자</div>
		<div><input type="text" name="bbsWriter" value="<%= bbs.getBbsWriter()%>"></div>
	</div>
	<div>
		<div>비밀번호</div>
		<div><input type="password" placeholder="비밀번호" name="bbsPassword"></div>
	</div>
	<div>
		<div>제목</div>
		<div><input type="text" name="bbsTitle" value="<%= bbs.getBbsTitle()%>"></div>
	</div>
	<div>
		<div>내용</div>
		<div><input type="textarea" rows="5" cols="13" name="bbsContent" value="<%= bbs.getBbsContent()%>" /></div>
	</div>
	<div>
		<div>파일첨부</div>
		<div><%= bbs.getBbsFile()%></div>
		<div><input type="file"></div>
	</div>
</div>
	<button type="button" onclick="location.href='main.jsp'">취소</button>
	<button type="submit">저장</button>
</form>
</body>
</html>