package com.study.boardspringvueback.vo;

import lombok.Data;

/**
 * 페이징 처리 및 검색 파라미터를 담아서 보내주는 기능을 담당
 * boardCategoryNo 검색 카테고리
 * startDate 검색 시작 작성일
 * endDate 검색 마지막 작성일
 * searchKeyWord 검색어
 * pageIndex 페이지 번호
 * viewPost 보여줄 게시글 갯수
 */
@Data
public class BoardPageSearchVO {
    private String startDate;
    private String endDate;
    private String boardCategoryNo;
    private String searchKeyWord;
    private int startPost;
    private int viewPost;

}
