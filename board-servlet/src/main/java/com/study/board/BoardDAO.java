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

//        SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
        SqlSession sqlSession = sessionFactory.openSession();//커넥션가지고 있는 세션열기
        return sqlSession.selectList("selectCategoryList");//단건조회
    }

    /**
     * 카테고리 1개 조회 메서드
     * 등록된 글의 카테고리 번호를 활용하여 db에서 다시 카테고리를 조회해서 읽어온다
     * @param boardCategoryName boardList(), boardRead()에서 받아오는 category number,
     */
//    public String readCategoryName(int boardCategoryName){
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String sql = "SELECT board_category_name FROM board_category WHERE board_category_no=?";
//
//        try {
//            BoardCategoryVO boardCategory = new BoardCategoryVO();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, boardCategoryName);
//            rs = pstmt.executeQuery();
//            if (rs.next()){
//                boardCategory.setBoardCategoryName(rs.getString(1));
//                return boardCategory.getBoardCategoryName();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(rs, pstmt, conn);
//        }
//        return null;
//    }
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
        Connection conn = DbOpen.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM board ";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            int a =0;
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbClose.close(rs, pstmt, conn);
        }
        return -1; //조회실패했을 때
    }

    public int boardGetLastPage(){
        int totalPost = 0;
        int firstPage = 0;
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
        viewUp(boardNo);

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
    public int boardDelete(int boardNo) {
        Connection conn = DbOpen.getConnection();
        PreparedStatement pstmt = null;
        String sql = "DELETE  FROM board WHERE board_no =?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);
            return pstmt.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            DbClose.close(pstmt, conn);
        }
        return -1; //데이터베이스 오류
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
     * @param boardNo: 게시글 번호
     */

    public void viewUp(int boardNo){
        SqlSession sqlSession = sessionFactory.openSession(true);
        sqlSession.update( "viewUp",boardNo);
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
