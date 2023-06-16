package com.study.boardspringvueback.vo;

import lombok.Data;

/**
 * 게시판 카테고리 카테고리 조회 관련 VO
 * boardCategoryNo 카테고리 번호(기본키)
 * boardCategoryName 카테고리 명
 */
@Data
public class BoardCategoryVO {
    private int boardCategoryNo;
    private String boardCategoryName;

}
