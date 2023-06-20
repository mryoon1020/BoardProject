<template>
    <h1>WriteView</h1>
    <form method="post" action="/write" id="writeForm">
<div>
	<div>
		<div>
			<h3>카테고리</h3>
			<select name="boardCategoryName" id="boardCategory">
				<!-- <c:forEach var="cateList" items="${cateList}">
					<option value="${cateList.boardCategoryName}">${cateList.boardCategoryName}</option>
				</c:forEach> -->
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
</template>