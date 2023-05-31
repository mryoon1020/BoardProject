package com.study.boardService;

import com.study.DBConnection.DbClose;
import com.study.DBConnection.DbOpen;
import com.study.boardCategory.BoardCategory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardServiceGetCategory implements BoardService{

//    @Override
//    public void runService() {
//
//        // getCategoryList() 메서드를 호출하고 결과를 처리하는 코드 작성
//        ArrayList<BoardCategory> categoryList = getCategoryList();
//
//        // 결과 처리
//        for (BoardCategory category : categoryList) {
//            System.out.println(category.getBoardCategoryNo());
//            System.out.println(category.getBoardCategoryName());
//            // 필요한 속성들을 가져와 처리
//        }
//    }
//
//    /**
//     * 카테고리 목록조회
//     *
//     * @return
//     */
//    public ArrayList<BoardCategory> getCategoryList() {
//        Connection conn = DbOpen.getConnection();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String sql = "SELECT * FROM board_category";
//
//        ArrayList<BoardCategory> list = new ArrayList<>();
//        try {
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                BoardCategory boardCategory = new BoardCategory();
//                boardCategory.setBoardCategoryNo(rs.getInt(1));
//                boardCategory.setBoardCategoryName(rs.getString(2));
//                list.add(boardCategory);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DbClose.close(rs, pstmt, conn);
//        }
//
//        return list;
//
//    }

    @Override
    public String runService(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
