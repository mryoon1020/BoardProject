package com.study.service;

import com.study.board.BoardDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardServiceReplyListAction implements BoardService{
    @Override
    public void runService(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        BoardDAO boardDAO = new BoardDAO();
        boardDAO.boardReplyList(boardNo);

    }
}
