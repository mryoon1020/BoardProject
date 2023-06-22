package com.study.boardspringvueback.controller;

import com.study.boardspringvueback.service.BoardService;
import com.study.boardspringvueback.vo.BoardPageSearchVO;
import com.study.boardspringvueback.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.study.boardspringvueback.util.MyUtility.checkNullChangeToEmptyString;

@RestController
public class BoardController {
    @Autowired
    private BoardService service;
//    @GetMapping("/")
//    public String home(){
//        System.out.println("들렀음");
//        return "준비중...";
//    }
    @GetMapping("/")
    public List home(){
        List list = new ArrayList<>();
        list.add("안녕");
        list.add("이러면 되냐?");
        list.add("됐으면 좋겠다");
        return list;
    }

    @GetMapping("/api")
    public String api(){
        System.out.println("/api 들렀음");
        return "api 로 호출한거 준비중...";
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

//        String currentPage = boardPageSearchVO.getPageIndex();
//        int iCurrentPage = 0;
//        int pageIndex = 0;
//        int viewPost = 10;
//        int totalPost = service.totalPost();
//        int lastPage = 0;
//
//        if("".equals(checkNullChangeToEmptyString(currentPage))){
//            currentPage = "1";
//        }else {
//            currentPage = request.getParameter("currentPage");
//        }
//
//        iCurrentPage = Integer.parseInt(currentPage);
//        pageIndex = (iCurrentPage-1)*viewPost;
//        lastPage = (int)Math.ceil((double)totalPost/viewPost);

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
        System.out.println("contTotalPost 들어왔다");
        System.out.println(totalPost);
        return totalPost;
    }

}
