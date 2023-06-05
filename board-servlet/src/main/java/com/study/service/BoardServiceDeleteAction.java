package com.study.service;

import com.study.board.BoardDAO;
import com.study.board.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * read.jsp 에서 form을 통해 boardPassword, boardNo를 전달 받아 처리하는 서비스
 *
 * boardNo : 전달받은 게시글 번호
 * flag : boardDAO.delet() 실행 결과를 확인하는 지표, deleteAction.jsp에서 활용됨
 * userPassword : read.jsp 에서 user가 입력한 비밀번호
 * boardDAO : BoardDAO class안의 메서드를 사용하기 위한 변수
 * checkPassword : BoardDAO().checkPassword(boardNo)를 통해 실제 저장된 비밀번호를 가져오기 위한 변수
 *
 */
public class BoardServiceDeleteAction implements BoardService{
    @Override
    public void runService(HttpServletRequest request, HttpServletResponse response) throws Exception{

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        boolean flag = false;
        String userPassword = request.getParameter("boardPassword");
        BoardDAO boardDAO = new BoardDAO();
        BoardVO checkPassword = new BoardDAO().checkPassword(boardNo);

        if(userPassword.equals(checkPassword.getBoardPassword())){

            boardDAO.boardDelete(boardNo);
            request.setAttribute("flag", flag);

            response.sendRedirect("/board");

        }else{

            response.sendRedirect("/board?action=read&boardNo="+boardNo);

        }

    }
}
