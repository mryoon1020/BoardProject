<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.study.board.BoardVO" %>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.study.board.BoardVO" %>
<%@ page import="com.study.boardCategory.BoardCategory" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.nio.charset.StandardCharsets" %>

<%
	/**
	 * 페이징 처리부분
	 * a태그 통해 'main.jsp?currentPage= 파라미터 전달
	 */
	String currentPage = request.getParameter("currentPage");
	if(currentPage == null){
		currentPage="1";
	}
	int iCurrentPage = Integer.parseInt(currentPage);
	int pageIndex = (iCurrentPage-1)*10;

	/**
	 * 검색처리부분
	 * if(startDate == "")
	 * if(endDate == "")
	 * if(keyWord == "")
	 * 상기 3개 사용이유 => 서버로 전송시 null이 아닌 ""로 인식되어 검색 쿼리오작동 유발방지
	 */
	int cate = 0;
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
	String keyWord = request.getParameter("keyWord");

	if(request.getParameter("cate") == null){
		cate = 0;
	}else{
			cate = Integer.parseInt(request.getParameter("cate"));
	}

	if(startDate == "") {
		startDate = null;
	}
	if(endDate == "") {
		endDate = null;
	}
	if(keyWord == "") {
		keyWord = null;
	}
	if(keyWord != null && keyWord != ""){
		keyWord = new String(keyWord.getBytes("ISO-8859-1"), "UTF-8"); //한글처리부분
	}


	HashMap map = new HashMap();
	map.put("pageIndex", pageIndex);
	map.put("cate", cate);
//	map.put("startDate", request.getParameter("startDate"));
//	map.put("endDate", request.getParameter("endDate"));
//	map.put("keyWord", request.getParameter("keyWord"));
	map.put("startDate", startDate);
	map.put("endDate", endDate);
	map.put("keyWord", keyWord);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width",initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css" />
<title>JSP 게시판</title>
	<script>



	</script>
</head>
<body>

<div class="container">
	<div class="row">
		<form method="post" action="main.jsp">
			<div>
				<div>등록일</div>
				<input type="date" name="startDate"> ~
				<input type="date" name="endDate">
			</div>
			<div>
				<select name="cate" id="boardCategory">
					<%
						BoardDAO boardDAO = new BoardDAO();
						ArrayList<BoardCategory> categoryList = boardDAO.getCategoryList();

						for(int i =0; i<categoryList.size(); i++){
					%>
					<option value="<%=categoryList.get(i).getBoardCategoryNo()%>"><%=categoryList.get(i).getBoardCategoryName()%></option>
					<%
						}
					%>
				</select>
			</div>
			<div>
				<input type="text" name="keyWord">
				<button type="submit">검색</button>
			</div>
		</form>
	</div>
	<div class="row">
		<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
			<thead>
			<tr>
				<th style="background-color:#eeeeee; text-align:center;">카테고리</th>
				<th style="background-color:#eeeeee; text-align:center;">제목</th>
				<th style="background-color:#eeeeee; text-align:center;">작성자</th>
				<th style="background-color:#eeeeee; text-align:center;">조회수</th>
				<th style="background-color:#eeeeee; text-align:center;">등록일시</th>
				<th style="background-color:#eeeeee; text-align:center;">수정일시</th>
			</tr>
			</thead>
			<tbody>
			<%
				ArrayList<BoardVO> boardList = boardDAO.boardList(map);
				for(int i = 0; i<boardList.size(); i++){
			%>
			<tr>
				<td><%= boardList.get(i).getBoardCategoryName()%></td>
				<td><a href="read.jsp?boardNo=<%= boardList.get(i).getBoardNo()%>">
					<%= boardList.get(i).getBoardTitle().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>") %></a>
				</td>
				<td><%= boardList.get(i).getBoardWriter()%></td>
				<td>0</td>
				<td><%= boardList.get(i).getBoardWriteDate().substring(0,11)+
						boardList.get(i).getBoardWriteDate().substring(11,13)+"시"+" "+
						boardList.get(i).getBoardWriteDate().substring(14,16)+"분" %></td>
				<%
					if(boardList.get(i).getBoardUpdateDate()==null){
				%>
				<td>-</td>
				<%
				}else{
				%>
				<td>
					<%= boardList.get(i).getBoardUpdateDate().substring(0,11)+
						boardList.get(i).getBoardUpdateDate().substring(11,13)+"시"+" "+
						boardList.get(i).getBoardUpdateDate().substring(14,16)+"분" %></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
			</tbody>
		</table>
		<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
	</div>
	<div style="width:1000px; text-align: center; margin-top: 10px;">
		<%
			/**
			 * 페이징 화면구현부
			 */
			int lastPage = boardDAO.boardGetLastPage();
			for(int i = 1; i<=lastPage; i++){
		%>
				<a href='main.jsp?currentPage=<%=i%>'><%=i%></a>
		<%
			}
		%>
	</div>
</div>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>

</body>
</html>