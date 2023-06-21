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
    @PostMapping("/board/list")
    public List list(@RequestBody BoardPageSearchVO boardPageSearchVO, HttpServletRequest request){

        String currentPage = boardPageSearchVO.getPageIndex();
        int iCurrentPage = 0;
        int pageIndex = 0;
        int viewPost = 10;
        int totalPost = service.totalPost();
        int lastPage = 0;

        if("".equals(checkNullChangeToEmptyString(currentPage))){
            currentPage = "1";
        }else {
            currentPage = request.getParameter("currentPage");
        }

        iCurrentPage = Integer.parseInt(currentPage);
        pageIndex = (iCurrentPage-1)*viewPost;
        lastPage = (int)Math.ceil((double)totalPost/viewPost);

//        System.out.println(boardPageSearchVO);
//        Map map = new HashMap();
//
//        map.put("startDate", checkNullChangeToEmptyString(request.getParameter("startDate")));
//        map.put("endDate", checkNullChangeToEmptyString(request.getParameter("endDate")));
//        map.put("boardCategory", checkNullChangeToEmptyString(request.getParameter("boardCategory")));
//        map.put("keyWord",checkNullChangeToEmptyString(request.getParameter("keyWord")));
//        map.put("pageIndex",pageIndex);
//        map.put("viewPost",viewPost);

        List<BoardVO> list = service.list(boardPageSearchVO);

        return list;
    }

    @GetMapping("/board/category")
    public List boardCategoryList(){
        List categoryList = service.categoryList();
        return categoryList;
    }
}
