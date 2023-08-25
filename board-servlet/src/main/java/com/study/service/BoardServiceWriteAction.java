package com.study.service;

import com.study.board.BoardDAO;
import com.study.board.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * write.jsp에서 form태그를 통해 값을 전달받아 처리하는 부분
 *
 * flag : boardDAO.write() 실행결과를 확인하는지표, writeAction.jsp에서 활용됨
 * boardCategory : 사용자가 입력한 게시글 카테고리
 * boardWriter : 사용자가 입력한 게시글 작성자
 * boardPassword : 사용자가 입력한 게시글 비밀번호
 * boardTitle : 사용자가 입력한 게시글 제목
 * boardContent : 사용자가 입력한 게시글 내용
 */

public class BoardServiceWriteAction implements BoardService{
    @Override
    public void runService(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String currentMethod = request.getMethod();
        if(currentMethod.equalsIgnoreCase("get")) {
            request.getRequestDispatcher("/WEB-INF/views/write.jsp").forward(request, response);
        } else {

            boolean flag = false;
            int boardCategory = Integer.parseInt(request.getParameter("boardCategory"));
            String boardWriter = request.getParameter("boardWriter");
            String boardPassword = request.getParameter("boardPassword");
            String boardTitle = request.getParameter("boardTitle");
            String boardContent = request.getParameter("boardContent");

            BoardVO boardVO = new BoardVO();
            BoardDAO boardDAO = new BoardDAO();
            boardVO.setBoardCategoryNo(boardCategory);
            boardVO.setBoardWriter(boardWriter);
            boardVO.setBoardPassword(boardPassword);
            boardVO.setBoardTitle(boardTitle);
            boardVO.setBoardContent(boardContent);

            boardDAO.write(boardVO);

//            response.sendRedirect("/board?action=list");
            request.getRequestDispatcher("/board?action=list").forward(request, response);
        }


    }
}
