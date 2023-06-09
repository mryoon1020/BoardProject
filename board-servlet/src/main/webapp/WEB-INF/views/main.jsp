
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.study.board.BoardVO" %>
<%@ page import="com.study.board.BoardDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.study.board.BoardCategoryVO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>main.jsp</h1>
<div class="container">
  <div class="row">
    <form method="post" action="/board?action=list">
      <div>
        <div>등록일</div>
        <input type="date" name="startDate"> ~
        <input type="date" name="endDate">
      </div>
      <div>
        <select name="cate" id="boardCategory">
          <%
            BoardDAO boardDAO = new BoardDAO();
            List<BoardCategoryVO> categoryList = boardDAO.getCategoryList();

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
        // boardList 변수로 결과 전달
        ArrayList<BoardVO> boardList = (ArrayList<BoardVO>) request.getAttribute("boardList");
        for(int i = 0; i<boardList.size(); i++){
      %>
      <tr>
        <td><%= boardList.get(i).getBoardCategoryName()%></td>
        <td><a href="/board?action=read&boardNo=<%= boardList.get(i).getBoardNo()%>">
          <%= boardList.get(i).getBoardTitle().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>") %></a>
        </td>
        <td><%= boardList.get(i).getBoardWriter()%></td>
        <td><%= boardList.get(i).getBoardView()%></td>
        <td><%= boardList.get(i).getBoardWriteDate() %></td>
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
    <a href="/board?action=write" class="btn btn-primary pull-right">글쓰기</a>
  </div>
  <div style="width:1000px; text-align: center; margin-top: 10px;">
    <%
      /**
       * 페이징 화면구현부
       */
      int lastPage = boardDAO.boardGetLastPage();
      for(int i = 1; i<=lastPage; i++){
    %>
    <a href='/board/main?currentPage=<%=i%>'><%=i%></a>
    <%
      }
    %>
  </div>
</div>

</body>
</html>
