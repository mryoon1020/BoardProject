package com.study.boardspringvueback.service;

import com.study.boardspringvueback.util.MyUtility;
import com.study.boardspringvueback.vo.BoardCategoryVO;
import com.study.boardspringvueback.vo.BoardPageSearchVO;
import com.study.boardspringvueback.vo.BoardReplyVO;
import com.study.boardspringvueback.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 서비스 메서드
 * 기존에 sevice interface를 상속 받아 override 하는 방식에서 변경함
 */
@Service("com.study.service.BoardService")
public class BoardService {

    @Autowired
    private BoardDAO boardDAO;
    /**
     * 카테고리 리스트 DAO호출 메서드
     * @return 카테고리 리스트 매퍼실행결과
     */
    public List<BoardCategoryVO> categoryList() {
        return boardDAO.categoryList();
    }

    /**
     * 게시글목록 조회 DAO호출 메서드, 컨트롤러에서 보내진 vo의 null을 체크한 후 DAO를 호출
     * @param boardPageSearchVO 페이징, 게시글 검색정보 ( keyWord, boardCategory, startDate, endDate, pageIndex, viewPost 정보가담김)
     * @return 게시글 리스트 매퍼실행결과
     */
    public List<BoardVO> list(BoardPageSearchVO boardPageSearchVO) {

        boardPageSearchVO = MyUtility.checkNullChangeToEmptyString(boardPageSearchVO);

        return boardDAO.list(boardPageSearchVO);
    }

    /**
     * 게시글 읽기 DAO호출 메서드
     * @param boardNo 게시글 번호
     * @return 게시글 번호를 통해 게시글 읽기 매퍼 실행결과
     */
    public BoardVO read(int boardNo) {
        return boardDAO.read(boardNo);
    }

    /**
     * 조회수 증가 DAO호출 메서드, controller의 read()안에서 동시 실행됨
     * @param boardView 현재 게시글 조회수
     * @param boardNo 게시글 번호
     */
    public void viewUp(int boardView, int boardNo) {
        boardDAO.viewUp(boardView,boardNo);
    }

    /**
     * 게시글 작성 DAO호출 메서드
     * 제목, 비밀번호, 카테고리 번호를 검사하여 유효하지 않으면 false를 반환
     * @param boardVO 화면에서 작성된 내용이 담긴 객체
     * @return boolean 데이터 유효성 검사를 통과하면 true, 통과하지 못하면 false
     */
    public boolean write(BoardVO boardVO) {

        boolean emptyTitle = MyUtility.checkNullEmptyString(boardVO.getBoardTitle());
        boolean emptyPassword = MyUtility.checkNullEmptyString(boardVO.getBoardPassword());
        boolean emptyContent = MyUtility.checkNullEmptyString(boardVO.getBoardContent());
        boolean zeroCategoryNo = boardVO.getBoardCategoryNo() == 0;

        if(emptyPassword || emptyTitle || zeroCategoryNo || emptyContent) {
            return false;
        }

        boardDAO.write(boardVO);
        return true;
    }

    /**
     * 게시글 수정 DAO호출 메서드
     * @param boardVO 화면에서 작성된 내용이 담김
     */
    public void update(BoardVO boardVO) {
        boardDAO.update(boardVO);
    }

    /**
     * 게시글 비밀번호 확인 DAO호출 메서드
     * @param boardNo 게시글번호
     * @return 게시글 비밀번호
     */
    public BoardVO checkPassword(int boardNo) {
        return boardDAO.checkPassword(boardNo);
    }

    /**
     * 게시글 삭제 DAO호출 매서드
     * @param boardNo 게시글번호
     */
    public void delete(int boardNo) {
        boardDAO.delete(boardNo);
    }

    /**
     * 댓글 목록 조회 DAO호출 매서드
     * @param boardNo 게시글 번호
     * @return 게시글 목록
     */
    public List<BoardReplyVO> replyList(int boardNo) {
        return boardDAO.replyList(boardNo);
    }

    /**
     * 댓글 작성 DAO호출 메서드
     * @param boardReplyVO 화면에서 사용자가 입력한 내용이 담김
     */
    public void replyWrite(BoardReplyVO boardReplyVO) {
        boardDAO.replyWrite(boardReplyVO);
    }

    /**
     * 전체 게시글 갯수 조회 DAO호출 메서드, 페이징 처리시 사용
     * @return 전체게시글 갯수
     */
    public int totalPost() {
        return boardDAO.totalPost();
    }
}
