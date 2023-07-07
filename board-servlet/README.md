# Servlet을 활용한 간단한 게시판 프로젝트

> 사용 기술

- JAVA
- gradle
- mySQL
- myBatis
- JSP/Servlet
- Javascript

> DB 설계

 ![erd](https://github.com/mryoon1020/BoardProject/blob/master/board-servlet/erd.png)

>기능 구성

- 간단 CRUD 게시판
- 생성
- 수정
- 목록(검색, 페이징)
- 삭제
- 댓글 입력/삭제
- 비밀번호 검증

> Flow Chart

- action => controller => service => DAO => 화면 순서로 진행
- action 파라미터를 통해 각 Service를 실행
- DAO는 쿼리 호출기능만 할수 있도록 최소화
- controller에 들어갈 post, get 요청 구분에 신경 쓸필요없음

![flow](https://github.com/mryoon1020/BoardProject/blob/master/board-servlet/flow.png)