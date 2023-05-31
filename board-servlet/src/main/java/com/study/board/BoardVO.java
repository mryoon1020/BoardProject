package com.study.board;

public class BoardVO {
    private int boardNo;
    private  int boardCategoryNo;
    private String boardTitle;
    private String boardWriter;
    private String boardPassword;
    private String boardContent;
    private String boardView;
    private String boardWriteDate;
    private String boardUpdateDate;
    private String boardCategoryName;

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public int getBoardCategoryNo() {
        return boardCategoryNo;
    }

    public void setBoardCategoryNo(int boardCategoryNo) {
        this.boardCategoryNo = boardCategoryNo;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }

    public String getBoardPassword() {
        return boardPassword;
    }

    public void setBoardPassword(String boardPassword) {
        this.boardPassword = boardPassword;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardView() {
        return boardView;
    }

    public void setBoardView(String boardView) {
        this.boardView = boardView;
    }

    public String getBoardWriteDate() {
        return boardWriteDate;
    }

    public void setBoardWriteDate(String boardWriteDate) {
        this.boardWriteDate = boardWriteDate;
    }

    public String getBoardUpdateDate() {
        return boardUpdateDate;
    }

    public void setBoardUpdateDate(String boardUpdateDate) {
        this.boardUpdateDate = boardUpdateDate;
    }

    public String getBoardCategoryName() {
        return boardCategoryName;
    }

    public void setBoardCategoryName(String boardCategoryName) {
        this.boardCategoryName = boardCategoryName;
    }
}
