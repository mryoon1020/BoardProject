<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.study.boardCategory.BoardCategory" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<title>JSP 게시판</title>

	<script>

		function check(){

			const boardCategoryNo = document.getElementById('boardCategory').value;
			const boardWriter = document.getElementById('boardWriter').value;
			const boardPassword = document.getElementById('boardPassword').value;
			const boardCheckPassword = document.getElementById('boardCheckPassword').value;
			const boardTitle = document.getElementById('boardTitle').value;
			const boardContent = document.getElementById('boardContent').value;

			if(!boardCategoryNo || !boardWriter || !boardPassword || !boardCheckPassword || !boardTitle || !boardContent){
				alert("입력안된 사항이 있습니다.");
			}else{
				if(boardPassword !== boardCheckPassword){
					alert("비밀번호가 일치하지 않습니다.");
				}else{
					if(boardCategoryNo === '1'){
						alert("카테고리를 선택해주세요.");
					}else {
						document.getElementById('writeForm').submit();
					}
				}
			}

		}

	</script>

</head>
<body>

<form method="post" action="writeAction.jsp" id="writeForm">
<div>
	<div>
		<div>
			<h3>카테고리</h3>
			<select name="boardCategory" id="boardCategory">
				<%
					BoardDAO boardDAO = new BoardDAO();
					ArrayList<BoardCategory> list = boardDAO.getCategoryList();

					for(int i =0; i<list.size(); i++){
				%>
				<option value="<%=list.get(i).getBoardCategoryNo()%>"><%=list.get(i).getBoardCategoryName()%></option>

				<%
					}
				%>

			</select>
		</div>

		<div>
			<h3>작성자</h3>
			<input type="text" name="boardWriter" id="boardWriter">
		</div>
		<div>
			<h3>비밀번호</h3>
			<input type="password" placeholder="비밀번호" name="boardPassword" id="boardPassword">
			<input type="password" name="boardCheckPassword" id="boardCheckPassword" placeholder="비밀번호 확인" >
		</div>

		<div>
			<h3>제목</h3>
			<input type="text" name="boardTitle" id="boardTitle">
		</div>
		<div>
			<h3>내용</h3>
			<input type="textarea" name="boardContent" id="boardContent"/>
		</div>
		<div>
			<h3>파일첨부</h3>
			<input type="file">
			<input type="file">
			<input type="file">
		</div>
		<button type="button" onclick="location.href='main.jsp'">취소</button>
		<button type="button" onclick="check()" >저장</button>
	</div>
</div>
</form>
</body>
</html>