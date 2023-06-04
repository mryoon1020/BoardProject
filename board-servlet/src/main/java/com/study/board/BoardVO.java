package com.study.board;

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
public class BoardVO {
    private int boardNo;
    private  int boardCategoryNo;
    private String boardTitle;
    private String boardWriter;
    private String boardPassword;
    private String boardContent;
    private int boardView;
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

    public int getBoardView() {
        return boardView;
    }

    public void setBoardView(int boardView) {
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
