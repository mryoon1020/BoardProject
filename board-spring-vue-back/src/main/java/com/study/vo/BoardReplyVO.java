package com.study.vo;

import lombok.Data;

/**
 * 게시판 댓글 관련 변수
 * boardReplyNo : 댓글번호
 * boardNo : 게시글번호
 * boardReplyContent : 댓글내용
 * boardReplyDate : 댓글 작성일자
 */
@Data
public class BoardReplyVO {
    private int boardReplyNo;
    private int boardNo;
    private String boardReplyContent;
    private String boardReplyDate;
}
