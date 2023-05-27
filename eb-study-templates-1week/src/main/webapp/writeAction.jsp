<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="com.study.board.BoardVO" %>
<jsp:useBean id="board" class="com.study.board.BoardDTO" scope="page"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<title>JSP 게시판</title>
</head>
<body>

	<%
		int boardCategory = Integer.parseInt(request.getParameter("boardCategory"));
		String boardWriter = request.getParameter("boardWriter");
		String boardPassword = request.getParameter("boardPassword");
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");

		BoardVO boardVO = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		boardVO.setBoardCategoryNo(boardCategory);
		boardVO.setBoardWriter(boardWriter);
		boardVO.setBoardPassword(boardPassword);
		boardVO.setBoardTitle(boardTitle);
		boardVO.setBoardContent(boardContent);


		int result = boardDAO.write(boardVO);

	%>
	<script>
		let result = <%=result%>;
		if(result == -1){
			alert("글쓰기에 실패했습니다.")
			history.back();
		}else{
			alert("글쓰기에 성공했습니다.");
			location.href="main.jsp";
		}
	</script>
</body>
</html>