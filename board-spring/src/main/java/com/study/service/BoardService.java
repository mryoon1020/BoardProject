package com.study.service;

import com.study.vo.BoardCategoryVO;
import com.study.vo.BoardReplyVO;
import com.study.vo.BoardVO;

import java.util.List;
public interface BoardService {
    List<BoardCategoryVO> categoryList();
    List<BoardVO> list(int pageIndex, int viewPost);
    BoardVO read(int boardNo);
    void view(int boardView, int boardNo);
    void write(BoardVO boardVO);
    void update(BoardVO boardVO);
    BoardVO checkPassword(int boardNo);
    void delete(int boardNo);
    List<BoardReplyVO> replyList(int boardNo);
    void replyWrite(BoardReplyVO boardReplyVO);
    int totalPost();
}
