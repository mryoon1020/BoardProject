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
@RequestMapping("/api")
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
     * 게시판 목록 조회 메서드
     * @param boardPageSearchVO : 검색조건 및 페이징처리를 위한 VO입니다.
     * @return list : 게시판 목록
     */
    @GetMapping("/board/list")
    public List list(BoardPageSearchVO boardPageSearchVO){
        System.out.println((boardPageSearchVO.getViewPost()));
        List<BoardVO> list = service.list(boardPageSearchVO);
        return list;
    }

    /**
     * 게시판 카테고리 조회 메서드
     * Front-end 검색창에서 사용됩니다
     * @return categoryList : 카테고리 목록
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
    @GetMapping("/board/read/{boardNo}")
    public BoardVO read(@PathVariable int boardNo){
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

    /**
     * Front-end 에서 받아온 데이터를 service 쪽으로 넘겨줍니다.
     * @param boardVO Front-end 에서 입력한 데이터
     */
    @PostMapping("/board/write")
    public boolean write(@RequestBody BoardVO boardVO) {

        boolean result = service.write(boardVO);

        return result;
    }



}
