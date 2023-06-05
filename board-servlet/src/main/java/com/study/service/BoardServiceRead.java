package com.study.service;

import com.study.board.BoardDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 읽기 서비스
 * main.jsp에서 게시글 클릭시 파라미터로 boardNo(게시글번호)가 넘어옴
 * Cotroller로 부터 호출받아서 boardDAO의 BoardRead()를 실행
 */
public class BoardServiceRead implements BoardService{
    @Override
    public void runService(HttpServletRequest request, HttpServletResponse response) throws Exception {

        BoardDAO boardDAO = new BoardDAO();
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        boardDAO.BoardRead(boardNo);

        request.getRequestDispatcher("/WEB-INF/views/read.jsp").forward(request, response);
    }
}
