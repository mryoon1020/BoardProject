package com.study.board;

import com.study.DBConnection.DbClose;
import com.study.DBConnection.DbOpen;
import com.study.config.SqlMapConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAO {

     private static SqlSessionFactory sessionFactory;
    static {
        sessionFactory = SqlMapConfig.getSqlMapInstance();
    }

    /**
     * 카테고리 목록조회
     * @return
     */
    public List<BoardCategoryVO> getCategoryList(){

        SqlSession sqlSession = sessionFactory.openSession();//커넥션가지고 있는 세션열기
        return sqlSession.selectList("selectCategoryList");//단건조회
    }

    /**
     * 글생성 매서드
     * @param boardVO : write.jsp에서 writeAction을 통해 넘어오는 데이터 저장객체
     * @return
     */
    public void write(BoardVO boardVO) {

        SqlSession sqlSession = sessionFactory.openSession(true);
        sqlSession.insert( "write",boardVO);
        sqlSession.close();

    }

    /**
     * 리스트 조회
     * @return
     */
    public List<BoardVO> boardList(Map map){
        SqlSession sqlSession = sessionFactory.openSession();
        return sqlSession.selectList("list", map);
    }

    /**
     * 전체 게시글 갯수 조회
     * 페이징처리에서 사용
     * @return
     */
    public int boardCountPost(){
        SqlSession sqlSession = sessionFactory.openSession();
        return sqlSession.selectOne("totalPost");
    }

    /**
     * 페이징 계산에 사용되는 요소
     * @return lastPage : 제일 마지막 페이지 번호
     */
    public int boardGetLastPage(){
        int totalPost = 0;
        int lastPage = 0;
        totalPost = boardCountPost();
        lastPage = (int)Math.ceil((double)totalPost/10);
        return lastPage;

    }

    /**
     *
     * 게시글 번호를 parameter로 전달받아 쿼리를 실행
     * boardView(boardVO) : 조회수 증가 메서드
     * @param boardNo 게시글 번호
     *
     */
    public BoardVO BoardRead(int boardNo) {

        SqlSession sqlSession = sessionFactory.openSession();

        return sqlSession.selectOne( "read",boardNo);
    }

    /**
     * 게시글 수정 메서드
     * update.jsp로 부터 controller를 거쳐 파라미터를 전달받아 쿼리실행
     * @param boardVO update.jsp로 부터 전달받은 파라미터
     */
    public void update(BoardVO boardVO) {

        SqlSession sqlSession = sessionFactory.openSession(true);
        sqlSession.update( "update",boardVO);
        sqlSession.close();

    }

    /**
     * 게시글 삭제 메서드
     * @param boardNo 게시글번호
     * @return
     */
    public void boardDelete(int boardNo) {
        SqlSession sqlSession = sessionFactory.openSession(true);
        sqlSession.delete("delete", boardNo);
        sqlSession.close();
    }

    /**
     * 비밀번호 검증 메서드
     * @param boardNo : 게시글번호
     * @return
     */
    public BoardVO checkPassword(int boardNo) {

        SqlSession sqlSession = sessionFactory.openSession();

        return sqlSession.selectOne( "checkPassword",boardNo);
    }

    /**
     * 조회수 증가 기능 메서드, boardRead()와 연동
     * @param boardVO: 게시글 정보(기존조회수를 꺼내오기 위함)
     */

    public void viewUp(BoardVO boardVO){
        SqlSession sqlSession = sessionFactory.openSession(true);
        sqlSession.update( "viewUp",boardVO);
        sqlSession.close();
    }

    /***
     * 게시판 댓글등록 기능 메서드
     */

    public void boardReplyWrite(Map map){

        SqlSession sqlSession = sessionFactory.openSession(true);
        sqlSession.insert("writeReply", map);
    }

    public List<BoardReplyVO> boardReplyList(int boardNo){

        SqlSession sqlSession = sessionFactory.openSession();

        return sqlSession.selectList("selectReplyList", boardNo);
    }

}
