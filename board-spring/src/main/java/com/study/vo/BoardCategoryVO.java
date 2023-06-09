package com.study.vo;

/**
 * 게시판 카테고리 카테고리 조회 관련 VO
 * boardCategoryNo 카테고리 번호(기본키)
 * boardCategoryName 카테고리 명
 */
public class BoardCategoryVO {
    private int boardCategoryNo;
    private String boardCategoryName;

    public int getBoardCategoryNo() {
        return boardCategoryNo;
    }

    public void setBoardCategoryNo(int boardCategoryNo) {
        this.boardCategoryNo = boardCategoryNo;
    }

    public String getBoardCategoryName() {
        return boardCategoryName;
    }

    public void setBoardCategoryName(String boardCategoryName) {
        this.boardCategoryName = boardCategoryName;
    }
}
