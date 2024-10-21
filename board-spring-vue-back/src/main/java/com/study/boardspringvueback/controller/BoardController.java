package com.study.boardspringvueback.controller;

import com.study.boardspringvueback.service.BoardService;
import com.study.boardspringvueback.vo.BoardPageSearchVO;
import com.study.boardspringvueback.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.study.boardspringvueback.util.MyUtility.checkNullChangeToEmptyString;


@RestController
public class BoardController {
    @Autowired
    private BoardService service;
    /**
     * home 메서드
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
    /**
     * list page 호출 메서드
     * currentPage : 현재페이지
     * iCurrentPage : 연산을 위해 int로 형변환한 currentPage
     * pageIndex : 쿼리에서 Limit 시작될 글 index
     * viewPost : 한페이지에 보여줄 게시글 갯수
     * totalPost: 전체 글갯수
     * lastPage : 마지막 페이지
     * @param request : jsp와 통신할 도구
     * @return main.jsp
     */
    @GetMapping("/board/list")
    public List list(BoardPageSearchVO boardPageSearchVO, HttpServletRequest request){
        List<BoardVO> list = service.list(boardPageSearchVO);
        return list;
    }

    /**
     * 게시판 카테고리조회 메서드
     * front에서 component로 쪼갰음
     * @return 카테고리 목록
     */
    @GetMapping("/board/category")
    public List boardCategoryList(){
        List categoryList = service.categoryList();
        return categoryList;
    }

    /**
     * pagenation 처리를 위해 조건에 맞는 게시글 목록을 조회
     * @return
     */
    @GetMapping("/board/countTotalPost")
    public int boardCountTotalPost(){
        int totalPost = service.totalPost();
        return totalPost;
    }

    /**
     * 게시글 조회 메서드
     * @param boardNo 게시글 번호
     * @return boardVO 게시글 내용
     */
    @GetMapping("/board/read")
    public BoardVO read(int boardNo){
        BoardVO boardVO = service.read(boardNo);
        service.viewUp(Integer.parseInt(boardVO.getBoardView()),boardNo);
        return boardVO;
    }

    /**
     *
     * @param boardVO 화면에서 사용자가 입력한 값(JSON형태로 들어옴)
     * @return 성공, 실패
     */
    @PostMapping("/board/update")
    public boolean update(@RequestBody BoardVO boardVO){

        System.out.println(boardVO);

        if(!checkPassword(boardVO.getBoardNo(),boardVO.getBoardPassword())){
            return false;
        } else {
            service.update(boardVO);
            return true;
        }
    }

    /**
     * 화면에서 받아온 사용자가 입력한 비밀번호와 저장되어있는 비밀번호 비교 메서드
     * @param boardNo 게시글번호
     * @param userEnteredPassword 사용자가 입력한 비밀번호
     * @return 비밀번호 결과가 일치하면 true, 일치하지 않은면 false를 반환
     */
    private boolean checkPassword(int boardNo, String userEnteredPassword) {
        BoardVO boardVO = service.checkPassword(boardNo);
        userEnteredPassword = checkNullChangeToEmptyString(userEnteredPassword);

        if (userEnteredPassword.equals(boardVO.getBoardPassword())) {
            return true;
        } else {
            return false;
        }
    }



}
