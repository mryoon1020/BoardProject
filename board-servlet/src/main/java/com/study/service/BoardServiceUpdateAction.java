package com.study.service;

import com.study.board.BoardDAO;
import com.study.board.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시글 수정을 위한 서비스 수행부, update.jsp 로 부터 Controller를 통해 데이터를 전달 받음
 * boardPassword : 사용자가 수정한 게시글 비밀번호
 * boardWriter : 사용자가 수정한 게시글 작성자
 * boardTitle : 사용자가 수정한 게시글 제목
 * boardContent : 사용자가 수정한 게시글 내용
 * 비밀번호 확인후 불일치시 비밀번호 오류 페이지로 넘어감
 */
public class BoardServiceUpdateAction implements BoardService{

    @Override
    public void runService(HttpServletRequest request, HttpServletResponse response) throws Exception{


        String currentMethod = request.getMethod();

        if(currentMethod.equalsIgnoreCase("get")) {
            request.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(request, response);
        } else {

            BoardVO boardVO = new BoardVO();
            String boardPassword = request.getParameter("boardPassword");
            String boardWriter = request.getParameter("boardWriter");
            String boardTitle = request.getParameter("boardTitle");
            String boardContent = request.getParameter("boardContent");
            String boardNo = request.getParameter("boardNo");

            boardVO.setBoardWriter(boardWriter);
            boardVO.setBoardTitle(boardTitle);
            boardVO.setBoardContent(boardContent);
            boardVO.setBoardPassword(boardPassword);
            boardVO.setBoardNo(Integer.parseInt(boardNo));


            BoardVO checkPassword = new BoardDAO().checkPassword(Integer.parseInt(boardNo));

            if(!boardPassword.equals(checkPassword.getBoardPassword())){
                request.getRequestDispatcher("/WEB-INF/views/passwordError.jsp").forward(request, response);
            }else{
                BoardDAO boardDAO = new BoardDAO();
                boardDAO.update(boardVO);
                response.sendRedirect("/board");
            }

        }


    }
}
