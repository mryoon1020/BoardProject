package com.study.service;

import com.study.vo.BoardCategoryVO;
import com.study.vo.BoardReplyVO;
import com.study.vo.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardMapper {
    List<BoardCategoryVO> categoryList();
    List<BoardVO> list(Map map);
    BoardVO read(int boardNo);
    void viewUp(int boardView,int boardNo);
    void write(BoardVO boardVO);
    void update(BoardVO boardVO);
    BoardVO checkPassword(int boardNo);
    void delete(int boardNo);
    List<BoardReplyVO> replyList(int boardNo);
    void replyWrite(BoardReplyVO boardReplyVO);
    int totalPost();
}
