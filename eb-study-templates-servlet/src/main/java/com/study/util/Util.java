package com.study.util;

import com.study.DBConnection.DbClose;
import com.study.DBConnection.DbOpen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Util {

    /**
     * 현재 월,일,시간 검색메서드
     * @// TODO: 2023-05-25  질문할것, 왜 이거 실행안되고 nullpoint exception 나오는지
     */
    public String getDate() {

        Connection conn = DbOpen.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT now()";
        System.out.println("getDate 실행");
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            String date = rs.getString(1);
            System.out.println("getDate 쿼리실행결과: " + date);
            if(rs.next()) {
                return date;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            DbClose.close(rs, pstmt, conn);
        }
        return ""; //데이터베이스 오류
    }

}
