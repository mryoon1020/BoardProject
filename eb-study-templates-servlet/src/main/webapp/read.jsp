<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="com.study.board.BoardDTO"%>
<%@ page import="java.io.PrintWriter" %>
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
	int bbsNo = 0;
	if(request.getParameter("bbsNo") != null){
		bbsNo = Integer.parseInt(request.getParameter("bbsNo"));
	}
	BoardDTO bbs = new BoardDAO().bbsRead(bbsNo); %>
<div>
	<div>
		<div><%= bbs.getBbsWriter()%></div>
		<div><%= bbs.getBbsWDate()%>등록일시</div>
		<div><%= bbs.getBbsUDate()%>수정일시</div>
	</div>
	<div>
		<div><%= bbs.getCategory()%>카테고리</div>
		<div><%= bbs.getBbsTitle()%>제목</div>
		<div><%= bbs.getBbsViews()%>조회수</div>
	</div>
	<div>
		<div><%= bbs.getBbsContent()%>내용</div>
		<div><%= bbs.getBbsFile()%>첨부파일 이미지 + 첨부파일</div>
	</div>

	<div>
	댓글
	</div>
	<div>
		<button type="button" onclick="location.href='main.jsp'">목록</button>
		<button onclick="location.href='update.jsp?bbsNo=<%= bbs.getBbsNo()%>'">수정</button>
		<button type="button" id="modal_open_btn" onclick="modalOpen()">삭제</button>
	</div>
	<div id="modal">

		<div class="modal_content">
			<h2>비밀번호 확인</h2>
			<div>비밀번호</div>
			<div><input type="password" placeholder="비밀번호" name="bbsPassword"></div>
			<div>
			<button onclick="exDelete()">삭제</button>
			<button type="button" id="modal_close_btn" onclick="modalClose()">취소</button>
			</div>
		</div>

		<div class="modal_layer"></div>
	</div>
</div>
<script>
	function exDelete(){
		confirm("정말로 삭제하시겠습까?")
				.then(location.href="deleteAction.jsp?bbsNo=<%=bbsNo%>")
				.error(history.back());
	}
</script>
</body>
</html>