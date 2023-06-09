package com.study.vo;

/**
 * 게시판 댓글 관련 변수
 */
public class BoardReplyVO {
    private int boardReplyNo;
    private String boardReplyContent;
    private String boardReplyDate;
    public int getBoardReplyNo() {
        return boardReplyNo;
    }

    public void setBoardReplyNo(int boardReplyNo) {
        this.boardReplyNo = boardReplyNo;
    }

    public String getBoardReplyContent() {
        return boardReplyContent;
    }

    public void setBoardReplyContent(String boardReplyContent) {
        this.boardReplyContent = boardReplyContent;
    }

    public String getBoardReplyDate() {
        return boardReplyDate;
    }

    public void setBoardReplyDate(String boardWriteDate) {
        this.boardReplyDate = boardWriteDate;
    }
}
