package com.study.boardspringvueback.service;

import com.study.boardspringvueback.vo.BoardCategoryVO;
import com.study.boardspringvueback.vo.BoardReplyVO;
import com.study.boardspringvueback.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("com.study.service.BoardServiceImpl")
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper mapper;
    @Override
    public List<BoardCategoryVO> categoryList() {
        return mapper.categoryList();
    }

    @Override
    public List<BoardVO> list(Map map) {
        return mapper.list(map);
    }

    @Override
    public BoardVO read(int boardNo) {
        return mapper.read(boardNo);
    }
    @Override
    public void viewUp(int boardView, int boardNo) {
        mapper.viewUp(boardView,boardNo);
    }
    @Override
    public void write(BoardVO boardVO) {
        mapper.write(boardVO);
    }

    @Override
    public void update(BoardVO boardVO) {
        mapper.update(boardVO);
    }

    @Override
    public BoardVO checkPassword(int boardNo) {
        return mapper.checkPassword(boardNo);
    }

    @Override
    public void delete(int boardNo) {
        mapper.delete(boardNo);
    }

    @Override
    public List<BoardReplyVO> replyList(int boardNo) {
        return mapper.replyList(boardNo);
    }

    @Override
    public void replyWrite(BoardReplyVO boardReplyVO) {
        mapper.replyWrite(boardReplyVO);
    }

    @Override
    public int totalPost() {
        return mapper.totalPost();
    }
}
