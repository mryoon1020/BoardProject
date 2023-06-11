<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<title>JSP 게시판</title>
	<script>

		function checkPasswordNull(){
			const boardPassword = document.getElementById('boardPassword').value;
			if(!boardPassword){
				alert("비밀번호를 입력해주세요");
			}else{
				document.getElementById('updateForm').submit();
			}
		}

	</script>
</head>
<body>

<form method="post" action="/update" id="updateForm">
<div>
	<div>
		<input type="hidden" name="boardNo" value="${boardVO.boardNo}">
		<div>카테고리</div>
		<div>${boardVO.boardCategoryName}</div>
	</div>
	<div>
		<div>등록일시</div>
		<div>${boardVO.boardWriteDate}</div>
	</div>
	<div>
		<div>수정일시</div>
		<div>${boardVO.boardUpdateDate}</div>
	</div>
	<div>
		<div>작성자</div>
		<div><input type="text" name="boardWriter" value="${boardVO.boardWriter}"></div>
	</div>
	<div>
		<div>비밀번호</div>
		<div><input type="password" placeholder="비밀번호" name="boardPassword" id="boardPassword"></div>
	</div>
	<div>
		<div>제목</div>
		<div><input type="text" name="boardTitle" value="${boardVO.boardTitle}"></div>
	</div>
	<div>
		<div>내용</div>
		<div><input type="textarea" rows="5" cols="13" name="boardContent" value="${boardVO.boardContent}" /></div>
	</div>
	<div>
		<div>파일첨부</div>
		<div><%-- boardVO.getBoardFile()--%></div>
		<div><input type="file"></div>
	</div>
</div>
	<button type="button" onclick="location.href='main.jsp'">취소</button>
	<button type="button" onclick="checkPasswordNull()">저장</button>
</form>
</body>
</html>