package com.study.vo;

import lombok.Data;

/**
 *
 * board 데이터 전송시 데이터를 담는 역할을 하는 클래스
 * boardNo 게시글 번호
 * boardCategoryNo 카테고리 번호
 * boardTitle 게시글 제목
 * boardWriter 게시글 작성자
 * boardPassword 게시글 비밀번호
 * boardContent 게시글 내용
 * boardView 게시글 조회수
 * boardWriteDate 게시글 작성일
 * boardUpdateDate 게시글 수정일
 * boardCategoryName 카테고리 이름
 */
@Data
public class BoardVO {
    private int boardNo;
    private  int boardCategoryNo;
    private String boardCategoryName;
    private String boardTitle;
    private String boardWriter;
    private String boardPassword;
    private String boardContent;
    private int boardView;
    private String boardWriteDate;
    private String boardUpdateDate;
}
