package com.study.controller;

import com.study.service.BoardServiceImpl;
import com.study.vo.BoardReplyVO;
import com.study.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.study.util.MyUtility.checkNullToEmptyString;

/**
 * BoardService 관련 컨트롤러
 * service : service호출을 위한 전역변수
 */
@Controller
public class BoardController {

    @Autowired
    @Qualifier("com.study.service.BoardServiceImpl")
    private BoardServiceImpl service;

    /**
     * index page 호출 메서드
     * @return
     */
    @Autowired
    @GetMapping("/")
    public String home(){
        return "index";
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
    @RequestMapping("/list")
    public String list(HttpServletRequest request){

        String currentPage = request.getParameter("currentPage");
        int iCurrentPage = 0;
        int pageIndex = 0;
        int viewPost = 10;
        int totalPost = service.totalPost();
        int lastPage = 0;

        Map map = new HashMap<>();

        if("".equals(checkNullToEmptyString(currentPage))){
            currentPage="1";
        }else {
            currentPage=request.getParameter("currentPage");
        }

        iCurrentPage = Integer.parseInt(currentPage);
        pageIndex = (iCurrentPage-1)*viewPost;
        lastPage = (int)Math.ceil((double)totalPost/viewPost);

        map.put("startDate",request.getParameter("startDate"));
        map.put("endDate",request.getParameter("endDate"));
        map.put("boardCategory",request.getParameter("boardCategory"));
        map.put("keyWord",request.getParameter("keyWord"));
        map.put("pageIndex",pageIndex);
        map.put("viewPost",viewPost);

        List<BoardVO> list = service.list(map);

        request.setAttribute("lastPage", lastPage);
        request.setAttribute("cateList",  service.categoryList());
        request.setAttribute("list", list);

        return "main";
    }

    /**
     * 게시글 작성 양식 호출 메서드
     */
    @GetMapping("/write")
    public String write(HttpServletRequest request){
        request.setAttribute("cateList",  service.categoryList());
        return "/write";
    }

    /**
     * 게시글 작성 메서드
     */
    @PostMapping("/write")
    public String write(BoardVO boardVO){
        service.write(boardVO);
        return "redirect:list";
    }

    /**
     * 게시글 조회 메서드
     * @param boardNo 게시글번호
     * @param request read.jsp에 쿼리실행결과를 뿌려줄 도구
     * @return
     */
    @GetMapping("/read")
    public  String read(int boardNo, HttpServletRequest request){

        int boardView = 0;
        BoardVO boardVO = service.read(boardNo);
        boardView = boardVO.getBoardView();

        service.viewUp(boardView,boardNo);
        replyList(boardNo, request);
        request.setAttribute("boardVO",boardVO);
        return "/read";
    }

    /**
     * 글수정양식 호출메서드
     * @param boardNo 게시글번호
     * @param request jsp에 boardVO 전달 수단
     * @return POST로 받는 오버로딩된 update 메서드
     */
    @GetMapping("/update")
    public String update(int boardNo, HttpServletRequest request){
        BoardVO boardVO = service.read(boardNo);
        request.setAttribute("boardVO",boardVO);
        return "/update";
    }

    /**
     * 글수정 처리 메서드
     * @param boardVO update.jsp의 form 태그를 통해 전달받은 값
     * @param request checkPassword()를 사용하기 위한 수단, 사용자가 입력한 비밀번호를 전달해준다
     * @return 비밀번호 체크후 수정 성공시 /read 호출 실패시 passwordError.jsp 호출
     */
    @PostMapping("/update")
    public String update(BoardVO boardVO, HttpServletRequest request){
        boolean passwordCheck = checkPassword(boardVO.getBoardNo(), request);

        if(passwordCheck){
            service.update(boardVO);
            return "/read";
        }else{
            return "passwordError";
        }
    }

    /**
     * update.jsp, read.jsp 에서 받아온 사용자가 입력한 비밀번호와 저장되어있는 비밀번호 비교 메서드
     * @param boardNo 게시글번호
     * @param request POST update()에서 받은 사용자가 입력한 비밀번호를 꺼낼때 사용
     * @return 비밀번호 결과가 일치하면 true, 일치하지 않은면 false를 반환
     */
    private boolean checkPassword(int boardNo, HttpServletRequest request){
        BoardVO boardVO = service.checkPassword(boardNo);
        String enteredPassword = request.getParameter("boardPassword");

        if(enteredPassword.equals(boardVO.getBoardPassword())){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 게시글 삭제 메서드, 비밀번호 체크후 삭제
     * @param boardNo 게시글번호
     * @param request checkPassword()를 사용하기 위한 수단, 사용자가 입력한 비밀번호를 전달해준다
     * @return 비밀번호 체크후 삭제 성공시 /list 호출 실패시 passwordError.jsp 호출
     */
    @PostMapping("/delete")
    public String delete(int boardNo, HttpServletRequest request){

        boolean passwordCheck = checkPassword(boardNo, request);

        if(passwordCheck){
            service.delete(boardNo);
            return "redirect:/list";
        }else{
            return "passwordError";
        }

    }

    /**
     * 댓글목록 가져오는 메서드
     * @param boardNo 게시글 번호
     * @param request read.jsp에 댓글목록을 담아서 보내줄 도구
     */
    public void replyList(int boardNo, HttpServletRequest request){
        request.setAttribute("replyList", service.replyList(boardNo));
    }

    /**
     * 댓글작성 메서드
     * @param boardReplyVO read.jsp에서 form 태그를 통해 입력한 댓글 값을 받아올 도구
     * @param request 현재페이지의 header정보를 읽어올 도구
     * @return 댓글작성후 페이지 새로고침(boardNo가 존재하는 /read 호출
     */
    @PostMapping("/replyWrite")
    public String replyWrite(BoardReplyVO boardReplyVO, HttpServletRequest request){
        String boardNo = request.getParameter("boardNo");
        service.replyWrite(boardReplyVO);
        return "redirect:read?boardNo="+boardNo;
    }

}
