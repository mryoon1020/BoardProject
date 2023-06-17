package com.study.boardspringvueback.service;

public class BoardDAOold {

//     private static SqlSessionFactory sessionFactory;
//    static {
//        sessionFactory = SqlMapConfig.getSqlMapInstance();
//    }
//
//    /**
//     * 카테고리 목록조회
//     * @return
//     */
//    public List<BoardCategoryVO> getCategoryList(){
//
////        SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
//        SqlSession sqlSession = sessionFactory.openSession();//커넥션가지고 있는 세션열기
//        return sqlSession.selectList("selectCategoryList");//단건조회
//    }
//
//    /**
//     * 카테고리 1개 조회 메서드
//     * 등록된 글의 카테고리 번호를 활용하여 db에서 다시 카테고리를 조회해서 읽어온다
//     * @param boardCategoryName boardList(), boardRead()에서 받아오는 category number,
//     */
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
//    /**
//     * 글생성 매서드
//     * @param boardVO : write.jsp에서 writeAction을 통해 넘어오는 데이터 저장객체
//     * @return
//     */
//    public int write(BoardVO boardVO) {
//
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        String sql = "INSERT INTO board(board_category_no, board_title, board_writer, board_password, board_content," +
//                "board_view, board_write_date) " +
//                "VALUES(?,?,?,?,?,?,now());";
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//
//            pstmt.setInt(1, boardVO.getBoardCategoryNo());
//            pstmt.setString(2, boardVO.getBoardTitle());
//            pstmt.setString(3, boardVO.getBoardWriter());
//            pstmt.setString(4, boardVO.getBoardWriter());
//            pstmt.setString(5, boardVO.getBoardContent());
//            pstmt.setInt(6,0);
//
//            return pstmt.executeUpdate();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(pstmt, conn);
//        }
//        return -1; //데이터베이스 오류
//    }
//
//    /**
//     * 리스트 조회
//     * @return
//     */
//    public ArrayList<BoardVO> boardList(Map map){
//
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String sql = "SELECT * FROM board";
//        int viewPost = 10;
//
//        int pageIndex = (int) map.get("pageIndex");
//        int cate = (int) map.get("cate");
//        String startDate = (String) map.get("startDate");
//        String endDate = (String) map.get("endDate");
//        String keyWord = (String) map.get("keyWord");
//
//        if(keyWord!=null) {
//            sql += " WHERE board_title LIKE '%" + keyWord + "%'"
//                    + " OR board_writer LIKE '%" + keyWord + "%'"
//                    + " OR board_content LIKE '%" + keyWord + "%'";
//            if (cate != 0 && cate != 1) {
//                sql += " AND board_category_no=" + cate;
//                if (startDate != null) {
//                    if (endDate != null) {
//                        sql += " AND board_write_date BETWEEN '" + startDate + "' AND '" + endDate + "'";
//                    } else {
//                        sql += " AND board_write_date > '" + startDate + "'";
//                    }
//                } else {
//                    if (endDate != null) {
//                        sql += " AND board_write_date < '" + endDate + "'";
//                    }
//                }
//            } else {
//                if (startDate != null) {
//                    if (endDate != null) {
//                        sql += " AND board_write_date BETWEEN '" + startDate + "' AND '" + endDate + "'";
//                    } else {
//                        sql += " AND board_write_date >= '" + startDate + "'";
//                    }
//                } else {
//                    if (endDate != null) {
//                        sql += " AND board_write_date <= '" + endDate + "'";
//                    }
//                }
//            } // cate != 0 의 else end
//            // keyWord!=null 의 else end
//        } else {
//            if (cate != 0 && cate != 1) {
//                sql += " WHERE board_category_no=" + cate;
//                if (startDate != null) {
//                    if (endDate != null) {
//                        sql += " AND board_write_date BETWEEN '" + startDate + "' AND '" + endDate + "'";
//                    } else {
//                        sql += " AND board_write_date >= '" + startDate + "'";
//                    }
//                } else {
//                    if (endDate != null) {
//                        sql += " AND board_write_date <= '" + endDate + "'";
//                    }
//                }
//            } else {
//                if (startDate != null) {
//                    if (endDate != null) {
//                        sql += " WHERE board_write_date BETWEEN '" + startDate + "' AND '" + endDate + "'";
//                    } else {
//                        sql += " WHERE board_write_date >= '" + startDate + "'";
//                    }
//                } else {
//                    if (endDate != null) {
//                        sql += " WHERE board_write_date <= '" + endDate + "'";
//                    }
//                }
//            }
//        }
//        sql += " ORDER BY board_No DESC LIMIT "+ pageIndex + ", " + viewPost;
//
//        ArrayList<BoardVO> list = new ArrayList<BoardVO>();
//        Map listParameterMap = new HashMap<>();
//        try {
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            while(rs.next()) {
//                BoardVO boardVO = new BoardVO();
//                boardVO.setBoardNo(rs.getInt(1));
//                boardVO.setBoardCategoryNo(rs.getInt(2));
//                boardVO.setBoardTitle(rs.getString(3));
//                boardVO.setBoardWriter(rs.getString(4));
//                boardVO.setBoardPassword(rs.getString(5));
//                boardVO.setBoardContent(rs.getString(6));
//                boardVO.setBoardWriteDate(rs.getString(7));
//                boardVO.setBoardUpdateDate(rs.getString(8));
//                boardVO.setBoardCategoryName(readCategoryName(rs.getInt(2)));
//                list.add(boardVO);
//            }
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(rs, pstmt, conn);
//        }
//        return list;
//    }
//
//    /**
//     * 전체 게시글 갯수 조회
//     * 페이징처리에서 사용
//     * @return
//     */
//    public int boardCountPost(){
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String sql = "SELECT count(*) FROM board ";
//        try {
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery(sql);
//            int a =0;
//            if(rs.next()){
//                return rs.getInt(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(rs, pstmt, conn);
//        }
//        return -1; //조회실패했을 때
//    }
//
//    public int boardGetLastPage(){
//        int totalPost = 0;
//        int firstPage = 0;
//        int lastPage = 0;
//        totalPost = boardCountPost();
//        lastPage = (int)Math.ceil((double)totalPost/10);
//        return lastPage;
//
//    }
//
//    /**
//     *
//     * 게시글 번호를 parameter로 전달받아 쿼리를 실행
//     * boardView(boardVO) : 조회수 증가 메서드
//     * @TODO 조회수가 2씩 증가하는 문제가 있음
//     * @param boardNo 게시글 번호
//     *
//     */
//    public BoardVO BoardRead(int boardNo) {
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String sql = "SELECT * FROM board WHERE board_no = ?";
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1,boardNo);
//            rs = pstmt.executeQuery();
//            if(rs.next()) {
//                BoardVO boardVO = new BoardVO();
//                boardVO.setBoardNo(rs.getInt(1));
//                boardVO.setBoardCategoryName(readCategoryName(rs.getInt(2)));
//                boardVO.setBoardTitle(rs.getString(3));
//                boardVO.setBoardWriter(rs.getString(4));
//                boardVO.setBoardPassword(rs.getString(5));
//                boardVO.setBoardContent(rs.getString(6));
//                boardVO.setBoardWriteDate(rs.getString(8));
//                boardVO.setBoardUpdateDate(rs.getString(9));
//                boardVO.setBoardView(rs.getInt(7));
//                boardView(boardVO);
//                return boardVO;
//            }
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(rs, pstmt, conn);
//        }
//        return null;
//    }
//
//    /**
//     * 게시글 수정 메서드
//     * update.jsp로 부터 controller를 거쳐 파라미터를 전달받아 쿼리실행
//     * @param boardVO update.jsp로 부터 전달받은 파라미터
//     */
//    public int update(BoardVO boardVO) {
//
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        String sql = "UPDATE board SET board_writer=?,  board_title =?, board_content =?, board_update_date=NOW() WHERE board_no =?";
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, boardVO.getBoardWriter());
//            pstmt.setString(2, boardVO.getBoardTitle());
//            pstmt.setString(3, boardVO.getBoardContent());
//            pstmt.setInt(4, boardVO.getBoardNo());
//
//            return pstmt.executeUpdate();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(pstmt, conn);
//        }
//
//        return -1; //데이터베이스 오류
//    }
//
//    /**
//     * 게시글 삭제 메서드
//     * @param boardNo 게시글번호
//     * @return
//     */
//    public int boardDelete(int boardNo) {
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        String sql = "DELETE  FROM board WHERE board_no =?";
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, boardNo);
//            return pstmt.executeUpdate();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(pstmt, conn);
//        }
//        return -1; //데이터베이스 오류
//    }
//
//    /**
//     * 비밀번호 검증 메서드
//     * @param boardNo : 게시글번호
//     * @return
//     */
//    public BoardVO checkPassword(int boardNo) {
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String sql = "SELECT board_password FROM board WHERE board_no=?";
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, boardNo);
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                BoardVO boardVO = new BoardVO();
//                boardVO.setBoardPassword(rs.getString(1));
//                return boardVO;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(rs, pstmt, conn);
//        }
//        return null;
//    }
//
//    /**
//     * 조회수 증가 기능 메서드, boardRead()와 연동
//     * @param boardVO : boardRead()를 통해서 조회된 VO
//     *                  board_no, 기존 board_view를 가져올때 사용
//     */
//
//    public int boardView(BoardVO boardVO){
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        String sql = "UPDATE board SET board_view=? WHERE board_no =?";
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1,boardVO.getBoardView()+1);
//            pstmt.setInt(2, boardVO.getBoardNo());
//
//            return pstmt.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            DbClose.close(pstmt, conn);
//        }
//        return -1;
//    }
//
//    /***
//     * 게시판 댓글등록 기능 메서드
//     */
//
//    public void boardReplyWrite(Map map){
//
//        SqlSession sqlSession = sessionFactory.openSession(true);
//        sqlSession.insert("writeReply", map);
//    }
//
//    public List<BoardReplyVO> boardReplyList(int boardNo){
//
//        SqlSession sqlSession = sessionFactory.openSession();
//
//        return sqlSession.selectList("selectReplyList", boardNo);
//    }

}
