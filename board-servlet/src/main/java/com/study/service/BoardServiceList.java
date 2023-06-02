package com.study.service;

import com.study.board.BoardDAO;
import com.study.board.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardServiceList implements BoardService{
    @Override
    public String runService(HttpServletRequest request, HttpServletResponse response) {
        BoardDAO boardDAO = new BoardDAO();

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
//            keyWord = new String(keyWord.getBytes("ISO-8859-1"), "UTF-8"); //한글처리부분
        }


        Map map = new HashMap();
        map.put("pageIndex", pageIndex);
        map.put("cate", cate);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("keyWord", keyWord);

        boardDAO.boardList(map);
        ArrayList<BoardVO> boardList = boardDAO.boardList(map);
        request.setAttribute("boardList", boardList);

        return "/WEB-INF/views/main.jsp";
    }
}
