package com.study.board;

import com.study.DBConnection.DbClose;
import com.study.DBConnection.DbOpen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardDAO {

    /**
     * 카테고리 목록조회
     * @return
     */
    public ArrayList<BoardCategoryVO> getCategoryList(){

        Connection conn = DbOpen.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM board_category";

        ArrayList<BoardCategoryVO> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                BoardCategoryVO boardCategory = new BoardCategoryVO();
                boardCategory.setBoardCategoryNo(rs.getInt(1));
                boardCategory.setBoardCategoryName(rs.getString(2));
                list.add(boardCategory);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbClose.close(rs, pstmt,conn);
        }

        return list;
    }

    /**
     * 카테고리 1개 조회 메서드
     * 등록된 글의 카테고리 번호를 활용하여 db에서 다시 카테고리를 조회해서 읽어온다
     * @param boardCategoryName boardList(), boardRead()에서 받아오는 category number,
     */
    public String readCategoryName(int boardCategoryName){
        Connection conn = DbOpen.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT board_category_name FROM board_category WHERE board_category_no=?";

        try {
            BoardCategoryVO boardCategory = new BoardCategoryVO();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardCategoryName);
            rs = pstmt.executeQuery();
            if (rs.next()){
                boardCategory.setBoardCategoryName(rs.getString(1));
                return boardCategory.getBoardCategoryName();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbClose.close(rs, pstmt, conn);
        }
        return null;
    }
    /**
     * 글생성 매서드
     * @param boardVO : write.jsp에서 writeAction을 통해 넘어오는 데이터 저장객체
     * @return
     */
    public int write(BoardVO boardVO) {

        Connection conn = DbOpen.getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO board(board_category_no, board_title, board_writer, board_password, board_content," +
                "board_view, board_write_date) " +
                "VALUES(?,?,?,?,?,?,now());";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, boardVO.getBoardCategoryNo());
            pstmt.setString(2, boardVO.getBoardTitle());
            pstmt.setString(3, boardVO.getBoardWriter());
            pstmt.setString(4, boardVO.getBoardWriter());
            pstmt.setString(5, boardVO.getBoardContent());
            pstmt.setInt(6,0);

            return pstmt.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            DbClose.close(pstmt, conn);
        }
        return -1; //데이터베이스 오류
    }

    /**
     * 리스트 조회
     * @return
     */
    public ArrayList<BoardVO> boardList(Map map){

        Connection conn = DbOpen.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM board";
        int viewPost = 10;

        int pageIndex = (int) map.get("pageIndex");
        int cate = (int) map.get("cate");
        String startDate = (String) map.get("startDate");
        String endDate = (String) map.get("endDate");
        String keyWord = (String) map.get("keyWord");

        if(keyWord!=null) {
            sql += " WHERE board_title LIKE '%" + keyWord + "%'"
                    + " OR board_writer LIKE '%" + keyWord + "%'"
                    + " OR board_content LIKE '%" + keyWord + "%'";
            if (cate != 0 && cate != 1) {
                sql += " AND board_category_no=" + cate;
                if (startDate != null) {
                    if (endDate != null) {
                        sql += " AND board_write_date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                    } else {
                        sql += " AND board_write_date > '" + startDate + "'";
                    }
                } else {
                    if (endDate != null) {
                        sql += " AND board_write_date < '" + endDate + "'";
                    }
                }
            } else {
                if (startDate != null) {
                    if (endDate != null) {
                        sql += " AND board_write_date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                    } else {
                        sql += " AND board_write_date >= '" + startDate + "'";
                    }
                } else {
                    if (endDate != null) {
                        sql += " AND board_write_date <= '" + endDate + "'";
                    }
                }
            } // cate != 0 의 else end
            // keyWord!=null 의 else end
        } else {
            if (cate != 0 && cate != 1) {
                sql += " WHERE board_category_no=" + cate;
                if (startDate != null) {
                    if (endDate != null) {
                        sql += " AND board_write_date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                    } else {
                        sql += " AND board_write_date >= '" + startDate + "'";
                    }
                } else {
                    if (endDate != null) {
                        sql += " AND board_write_date <= '" + endDate + "'";
                    }
                }
            } else {
                if (startDate != null) {
                    if (endDate != null) {
                        sql += " WHERE board_write_date BETWEEN '" + startDate + "' AND '" + endDate + "'";
                    } else {
                        sql += " WHERE board_write_date >= '" + startDate + "'";
                    }
                } else {
                    if (endDate != null) {
                        sql += " WHERE board_write_date <= '" + endDate + "'";
                    }
                }
            }
        }
        sql += " ORDER BY board_No DESC LIMIT "+ pageIndex + ", " + viewPost;

        ArrayList<BoardVO> list = new ArrayList<BoardVO>();
        Map listParameterMap = new HashMap<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                BoardVO boardVO = new BoardVO();
                boardVO.setBoardNo(rs.getInt(1));
                boardVO.setBoardCategoryNo(rs.getInt(2));
                boardVO.setBoardTitle(rs.getString(3));
                boardVO.setBoardWriter(rs.getString(4));
                boardVO.setBoardPassword(rs.getString(5));
                boardVO.setBoardContent(rs.getString(6));
                boardVO.setBoardWriteDate(rs.getString(7));
                boardVO.setBoardUpdateDate(rs.getString(8));
                boardVO.setBoardCategoryName(readCategoryName(boardVO.getBoardCategoryNo()));
                list.add(boardVO);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            DbClose.close(rs, pstmt, conn);
        }
        return list;
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

//    public BoardDTO bbsRead(int bbsNo) {
//        String SQL = "select * from bbs where bbsNo = ?";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(SQL);
//            pstmt.setInt(1, bbsNo);
//            rs = pstmt.executeQuery();
//            if(rs.next()) {
//                BoardDTO bbs = new BoardDTO();
//                bbs.setBbsNo(rs.getInt(1));
//                bbs.setCategory(rs.getString(2));
//                bbs.setBbsTitle(rs.getString(3));
//                bbs.setBbsWriter(rs.getString(4));
//                bbs.setBbsPassword(rs.getString(5));
//                bbs.setBbsContent(rs.getString(6));
//                bbs.setBbsWDate(rs.getString(8));
//                bbs.setBbsFile(rs.getString(10));
//                return bbs;
//            }
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public int update(int bbsNo, String bbsWriter, String bbsPassword, String bbsTitle, String bbsContent, String bbsFile) {
//        String SQL = "update bbs set bbsWriter=?,  bbsTitle =?, bbsContent =?, bbsUDate=?, bbsFile = ? where bbsNo =?";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(SQL);
//            pstmt.setString(1, bbsWriter);
//            pstmt.setString(2, bbsTitle);
//            pstmt.setString(3, bbsContent);
//            pstmt.setString(4, getDate());
//            pstmt.setString(5, bbsFile);
//            pstmt.setInt(6, bbsNo);
//
//            System.out.println("bbsNo :"+bbsNo);
//            System.out.println("bbsWriter :"+bbsWriter);
//            System.out.println("bbsTitle :"+bbsTitle);
//            System.out.println("bbsContent :"+bbsContent);
//            System.out.println("bbsUDate :"+getDate());
//            return pstmt.executeUpdate();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return -1; //데이터베이스 오류
//    }
//
//    public int delete(int bbsNo) {
//        String SQL = "delete  from bbs where bbsNo =?";
//        System.out.println("delete 함수쪽: "+bbsNo);
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(SQL);
//            pstmt.setInt(1, bbsNo);
//            return pstmt.executeUpdate();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return -1; //데이터베이스 오류
//    }
//
//    public BoardDTO checkPassword(int bbsNo) {
//        String SQL = "select bbsPassword from bbs where bbsNo=?";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(SQL);
//            pstmt.setInt(1, bbsNo);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                BoardDTO bbs = new BoardDTO();
//                bbs.setBbsPassword(rs.getString(1));
//                return bbs;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
