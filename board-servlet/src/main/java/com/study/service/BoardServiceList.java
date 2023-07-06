package com.study.service;

import com.study.board.BoardDAO;
import com.study.board.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main.jsp의 게시글 페이징, 목록 및 검색기능을 처리해주는 서비스
 */
public class BoardServiceList implements BoardService{
    @Override
    public void runService(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BoardDAO boardDAO = new BoardDAO();

        /**
         * 페이징 처리부분
         * a태그 통해 'main.jsp?currentPage= 파라미터
         * viewPost : 보여줄 페이지 갯수
         * currentPage : object타입, 전달 받은 현재 페이지
         * iCurrentPage : currentPage를 int 연산하기위한 변수
         */
        String currentPage = request.getParameter("currentPage");
        if(currentPage == null){
            currentPage="1";
        }
        int viewPost = 10;
        int iCurrentPage = Integer.parseInt(currentPage);
        int pageIndex = (iCurrentPage-1)*viewPost;


        /**
         * 검색처리부분
         * cate : 카테고리 번호
         * startDate : 검색하고자 하는 작성일의 시작일
         * endDate : 검색하고자 하는 작성일의 마지막날
         * keyWord : 검색어
         */
        int cate = 0;
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String keyWord = request.getParameter("keyWord");

        Map map = new HashMap();
        map.put("pageIndex", pageIndex);
        map.put("boardCategory", cate);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("keyWord", keyWord);
        map.put("viewPost", viewPost);


        boardDAO.boardList(map);
        List<BoardVO> boardList = boardDAO.boardList(map);
        request.setAttribute("boardList", boardList);

        request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);

    }
}
