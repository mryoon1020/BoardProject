package com.study.boardspringvueback.service;

import com.study.boardspringvueback.vo.BoardCategoryVO;
import com.study.boardspringvueback.vo.BoardPageSearchVO;
import com.study.boardspringvueback.vo.BoardReplyVO;
import com.study.boardspringvueback.vo.BoardVO;

import java.util.List;
import java.util.Map;

/**
 * /mybatis/board.xml 속 쿼리를 호출하는 클래스
 */
public interface BoardMapper {
    /**
     * 카테고리 조회 쿼리 호출
     * @return 쿼리결과
     */
    List<BoardCategoryVO> categoryList();

    /**
     * 게시글 조회 쿼리 호출, 검색조건과 연동
     * @param map 검색 조건이 담김
     * @return 쿼리결과
     */
    List<BoardVO> list(BoardPageSearchVO boardPageSearchVO);

    /**
     * 게시글 읽기 쿼리 호출
     * @param boardNo 게시글번호
     * @return 쿼리결과
     */
    BoardVO read(int boardNo);

    /**
     * 게시글 조회수 증가 쿼리 호출, controller의 read()와 연동
     * @param boardView 현재 게시글 조회수
     * @param boardNo 게시글 번호
     */
    void viewUp(int boardView,int boardNo);

    /**
     * 게시글 작성 쿼리 호출
     * @param boardVO 사용자가 입력한 내용
     */
    void write(BoardVO boardVO);

    /**
     * 게시글 수정 쿼리 호출
     * @param boardVO 사용자가 입력한 내용
     */
    void update(BoardVO boardVO);

    /**
     * 게시글 비밀번호 확인 쿼리 호출
     * @param boardNo 게시글 번호
     * @return 게시글 비밀번호
     */
    BoardVO checkPassword(int boardNo);

    /**
     * 게시글 삭제쿼리 호출
     * @param boardNo 게시글 번호
     */
    void delete(int boardNo);

    /**
     * 댓글목록 조회 쿼리 호출
     * @param boardNo 게시글 번호
     * @return 게시글과 게시글 번호가 일치하는 댓글 목록
     */
    List<BoardReplyVO> replyList(int boardNo);

    /**
     * 댓글 작성 쿼리호출
     * @param boardReplyVO 사용자가 입력한 내용
     */
    void replyWrite(BoardReplyVO boardReplyVO);

    /**
     * 전체 게시글 갯수 조회 쿼리 호출, 페이징할 때 사용
     * @return 전체 게시글 갯수
     */
    int totalPost();
}
