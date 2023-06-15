<template>
    <h1>ReadView</h1>
    <div>
	<div>
		<div>작성자</div>
		<!-- <div>${boardVO.boardWriter}</div> -->
		<div>등록일시</div>
		<!-- <div>${boardVO.boardWriteDate}</div> -->
		<div>수정일시</div>
		<!-- <div>${boardVO.boardUpdateDate}</div> -->
	</div>
	<div>
		<div>카테고리</div>
		<!-- <div>${boardVO.boardCategoryName}</div> -->
		<div>제목</div>
		<!-- <div>${boardVO.boardTitle}</div> -->
		<div>조회수</div>
		<!-- <div>${boardVO.boardView}</div> -->
	</div>
	<div>
		<div>내용</div>
		<!-- <div>${boardVO.boardContent}</div> -->
		<div>첨부파일 이미지 + 첨부파일</div>
	</div>

	<div>
		<h4>댓글</h4>

		<!-- <c:forEach var="replyList" items="${replyList}">
			<p>
				${replyList.boardReplyDate}<br>
				${replyList.boardReplyContent}
			</p>
			<hr>
		</c:forEach> -->

		<h4>댓글쓰기</h4>
		<form action="/replyWrite" method="post" id="replyForm">
			<div>
				<!-- <input type="hidden" name="boardNo" value="${boardVO.boardNo}"> -->
				<input type="text" name="boardReplyContent" id="boardReplyContent" placeholder="댓글을 입력해주세요">
				<button onclick="writeReply()">등록</button>
			</div>
		</form>
	</div>
	<div>
		<button type="button" onclick="location.href='/list'">목록</button>
		<button onclick="location.href='/update?boardNo=${boardVO.boardNo}'">수정</button>
		<button type="button" id="modal_open_btn" onclick="modalOpen()">삭제</button>
	</div>

	<div id="modal">

		<div class="modal_content">
			<form method="post" action="/delete" id="deleteForm">
			<h2>비밀번호 확인</h2>
			<div>비밀번호</div>
			<!-- <div><input type="hidden" name="boardNo"value="${boardVO.boardNo}" /></div> -->
			<div><input type="password" placeholder="비밀번호" name="boardPassword" id="deletePassword"></div>
			<div>
			<button onclick="exDelete()">삭제</button>
			<button type="button" id="modal_close_btn" onclick="modalClose()">취소</button>
			</div>
			</form>
		</div>

		<div class="modal_layer"></div>
	</div>
</div>
</template>