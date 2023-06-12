<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <script>

        function read(boardNo){
            let url = "read";
            url=+"?boardNo="+boardNo;
            location.href=url;
        }

    </script>
</head>
<body>
<h1>main.jsp</h1>
<div class="container">
  <div class="row">
    <form method="post" action="/list">
      <div>
        <div>등록일</div>
        <input type="date" name="startDate"> ~
        <input type="date" name="endDate">
      </div>
      <div>
        <select name="boardCategory" id="boardCategory">
            <c:forEach var="cateList" items="${cateList}">
                <option value="${cateList.boardCategoryNo}">${cateList.boardCategoryName}</option>
            </c:forEach>
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
        <c:forEach var = "boardVO" items="${list}">
            <tr>
                <td>${boardVO.boardCategoryName}</td>
                <td><a href="/read?boardNo=${boardVO.boardNo}">${boardVO.boardTitle}</a></td>
                <td>${boardVO.boardWriter}</td>
                <td>${boardVO.boardView}</td>
                <td>${boardVO.boardWriteDate}</td>
                <td>${boardVO.boardUpdateDate}</td>
            </tr>
        </c:forEach>
      </tbody>
    </table>
    <a href="/write" class="btn btn-primary pull-right">글쓰기</a>
  </div>
    <br>
  <div style="width:1000px; text-align: center; margin-top: 10px;">


      <c:forEach var="cnt" begin="1" end="${lastPage}">

          <a href="/list?currentPage=${cnt}">${cnt}</a>&nbsp;&nbsp;

      </c:forEach>


      <a href='/board/main?currentPage='></a>


  </div>
</div>
</body>
</html>
