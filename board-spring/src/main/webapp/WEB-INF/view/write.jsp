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
					if(!boardCategoryNo){
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

<form method="post" action="/write" id="writeForm">
<div>
	<div>
		<div>
			<h3>카테고리</h3>
			<select name="boardCategoryName" id="boardCategory">
				<c:forEach var="cateList" items="${cateList}">
					<option value="${cateList.boardCategoryName}">${cateList.boardCategoryName}</option>
				</c:forEach>
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
		<button type="button" onclick="history.back()">취소</button>
		<button type="button" onclick="check()" >저장</button>
	</div>
</div>
</form>
</body>
</html>