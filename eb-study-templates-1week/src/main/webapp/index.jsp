<%@ page import="com.study.connection.ConnectionTest" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<%--<a href="hello-servlet">Hello Servlet</a>--%>

<script>
    location.href = 'main.jsp';
</script>

<%--

    ConnectionTest t = new ConnectionTest();
    out.println(t.getConnection());

--%>

<a href="main.jsp">이동</a>

<div id="topbox">

    <table>

        <tr></tr>

    </table>

</div>

<div>

    <table>

        <tr>
            <th>카테고리</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>등록일시</th>
            <th>수정일시</th>
        </tr>
        <tr>
            <td>JAVA</td>
            <td>제목입니다.</td>
            <td>홍길동</td>
            <td>12</td>
            <td>2022.04.08</td>
            <td>2024.12.31</td>
        </tr>
    </table>

</div>

<a href="./eb-study-templates-1week/bbsRead.jsp">aaaaaa</a>

</body>
</html>
