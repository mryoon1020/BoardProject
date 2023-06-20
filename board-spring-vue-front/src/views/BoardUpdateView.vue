<template>
    <h1>updateView</h1>
    <form method="post" action="/update" id="updateForm">
<div>
	<div>
		<!-- <input type="hidden" name="boardNo" value="${boardVO.boardNo}"> -->
		<div>카테고리</div>
		<!-- <div>${boardVO.boardCategoryName}</div> -->
	</div>
	<div>
		<div>등록일시</div>
		<!-- <div>${boardVO.boardWriteDate}</div> -->
	</div>
	<div>
		<div>수정일시</div>
		<!-- <div>${boardVO.boardUpdateDate}</div> -->
	</div>
	<div>
		<div>작성자</div>
		<!-- <div><input type="text" name="boardWriter" value="${boardVO.boardWriter}"></div> -->
	</div>
	<div>
		<div>비밀번호</div>
		<!-- <div><input type="password" placeholder="비밀번호" name="boardPassword" id="boardPassword"></div> -->
	</div>
	<div>
		<div>제목</div>
		<!-- <div><input type="text" name="boardTitle" value="${boardVO.boardTitle}"></div> -->
	</div>
	<div>
		<div>내용</div>
		<!-- <div><input type="textarea" rows="5" cols="13" name="boardContent" value="${boardVO.boardContent}" /></div> -->
	</div>
	<div>
		<div>파일첨부</div>
		<!-- <div>boardVO.getBoardFile()</div> -->
		<div><input type="file"></div>
	</div>
</div>
	<button type="button" onclick="/boad/list">취소</button>
	<button type="button" onclick="checkPasswordNull()">저장</button>
</form>
</template>