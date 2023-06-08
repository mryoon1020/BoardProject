package com.study.service;

import com.study.board.BoardDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BoardServiceReplyWriteAction implements BoardService{
    @Override
    public void runService(HttpServletRequest request, HttpServletResponse response) throws Exception {

        BoardDAO boardDAO = new BoardDAO();
        Map map = new HashMap();

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        String boardReply = request.getParameter("boardReply");

        map.put("boardNo", boardNo);
        map.put("boardReply", boardReply);

        boardDAO.boardReplyWrite(map);

        response.sendRedirect("/board?action=read&boardNo="+boardNo);

    }
}
