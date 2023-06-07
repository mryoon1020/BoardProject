<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="com.study.board.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.study.board.BoardReplyVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<title>JSP 게시판</title>
	<style>
		#modal {
			display: none;

			position:relative;
			width:100%;
			height:100%;
			z-index:1;
		}

		#modal h2 {
			margin:0;
		}

		#modal button {
			display:inline-block;
			width:100px;
			margin-left:calc(100% - 100px - 10px);
		}

		#modal .modal_content {
			width:300px;
			margin:100px auto;
			padding:20px 10px;
			background:#fff;
			border:2px solid #666;
		}

		#modal .modal_layer {
			position:fixed;
			top:0;
			left:0;
			width:100%;
			height:100%;
			background:rgba(0, 0, 0, 0.5);
			z-index:-1;
		}
	</style>
	<script>

		function writeReply() {
			const value = document.getElementById("boardReply").value
			if (!value) {
				alert("내용을 입력해주세요")
			} else {
				document.getElementById("replyForm").submit();
			}
		}


	<%--function writeReply() {--%>
	<%--	const value = document.getElementById("boardReply").value--%>
	<%--	const form = document.getElementById("replyForm")--%>

	<%--	if(!value){--%>
	<%--		alert("내용을 입력해주세요")--%>
	<%--	}else{--%>
	<%--		document.getElementById("replyForm").submit();--%>
	<%--		form.addEventListener('submit',(e) =>{--%>
	<%--			e.preventDefault()--%>
	<%--		});--%>

	<%--		const dataSend = new FormData(form);--%>
	<%--		const data = new URLSearchParams(dataSend);--%>

	<%--		fetch("/board?action=reply&boardNo=<%= request.getParameter("boardNo")%>",{--%>
	<%--			method: 'POST',--%>
	<%--			body:	data,--%>
	<%--		}).then(response => {--%>
	<%--			if(response.ok){--%>
	<%--				reply();--%>
	<%--			}else{--%>

	<%--			}--%>
	<%--		})--%>
	<%--			.then(data => console.log(data))--%>
	<%--				.catch(error => console.log(error))	;--%>

	<%--	}--%>
	<%--}--%>

	<%--function reply(){--%>
	<%--	let replyList;--%>

	<%--	fetch("/board?action=replyList&boardNo=<%= request.getParameter("boardNo")%>",{--%>
	<%--		method:'GET',--%>
	<%--	}).then(response => {--%>
	<%--		response.json();})--%>
	<%--			.then(data => {--%>

	<%--				console.log(data);--%>

	<%--				// let replyListArea = document.getElementById('replyListArea');--%>
	<%--				// let replyList = document.createElement('p');--%>
	<%--				// let makeHr = document.getElementById('hr');--%>
	<%--				//--%>
	<%--				// replyList.setAttribute();--%>
	<%--				// replyList.innerHTML = data.--%>
	<%--				// replyListArea.setAttribute()--%>
	<%--			}--%>

	<%--			)--%>

	<%--}--%>

	function modalOpen(){
			document.getElementById("modal").style.display="block";
		}

		function modalClose(){
			document.getElementById("modal").style.display="none";
		}

	</script>
</head>
<body>
<%
	int boardNo = 0;
	if(request.getParameter("boardNo") != null){
		boardNo = Integer.parseInt(request.getParameter("boardNo"));
	}
	BoardVO boardVO = new BoardDAO().BoardRead(boardNo); %>
<div>
	<div>
		<div>작성자</div>
		<div><%= boardVO.getBoardWriter()%></div>
		<div>등록일시</div>
		<div><%= boardVO.getBoardWriteDate()%></div>
		<div>수정일시</div>
		<div><%= boardVO.getBoardUpdateDate()%></div>
	</div>
	<div>
		<div>카테고리</div>
		<div><%= boardVO.getBoardCategoryName()%></div>
		<div>제목</div>
		<div><%= boardVO.getBoardTitle()%></div>
		<div>조회수</div>
		<div><%= boardVO.getBoardView()%></div>
	</div>
	<div>
		<div>내용</div>
		<div><%= boardVO.getBoardContent()%></div>
		<div><%-- boardVO.getBoardFile() --%>첨부파일 이미지 + 첨부파일</div>
	</div>

	<div>

		<%
			BoardDAO boardDAO = new BoardDAO();
			List<BoardReplyVO> replyList = boardDAO.boardReplyList(boardVO.getBoardNo());

			for(int i=0; i<replyList.size(); i++){
		%>

		<p>
				<%=replyList.get(i).getBoardReplyDate()%><br>
				<%= replyList.get(i).getBoardReplyContent()%>
		<hr>
		</p>
		<%
			};
		%>

	댓글
		<div id="replyListArea">댓글창</div>
		<div>댓글입력</div>
		<div>댓글쓰기</div>
		<form action="/board?action=reply&boardNo=<%= request.getParameter("boardNo")%>" method="post" id="replyForm">
			<div>
				<input type="text" name="boardReply" id="boardReply" placeholder="댓글을 입력해주세요">
				<button onclick="writeReply()">등록</button>
			</div>
		</form>
	</div>
	<div>
		<button type="button" onclick="location.href='/board/main'">목록</button>
		<button onclick="location.href='/board?boardNo=<%= boardVO.getBoardNo()%>&action=update'">수정</button>
		<button type="button" id="modal_open_btn" onclick="modalOpen()">삭제</button>
	</div>
	<div id="modal">

		<div class="modal_content">
			<form method="post" action="/board?action=delete&boardNo=<%=	boardNo%>" id="deleteForm">
			<h2>비밀번호 확인</h2>
			<div>비밀번호</div>
			<div><input type="hidden" value="<%=boardNo%>" /></div>
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
<script>
	function exDelete(){
		const deletePassword = document.getElementById("deletePassword");
		if(!deletePassword){
			alert("비밀번호를 입력해주세요")
		}else {
			confirm("정말로 삭제하시겠습까?")
					.then(document.getElementById('deleteForm').submit())
		}
	}
</script>
</body>
</html>