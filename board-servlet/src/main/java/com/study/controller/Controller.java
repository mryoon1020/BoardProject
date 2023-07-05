package com.study.controller;

import com.mysql.cj.util.StringUtils;
import com.study.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/board/*")
public class Controller extends HttpServlet {
    Map<String, BoardService> commandMap = new HashMap<>();

    /**
     * 서블릿 요청일 왔을 때 최초 실행하여 map에 각 Action들에 대한 객체를 생성해줌
     *
     * boardList : 게시판 글 목록 조회
     * boardWriteAction : 게시글 생성동작 관여
     * boardRead : 게시글 읽기
     * boardUpdateAction : 게시글 수정동작 관여
     * boardDeleteAction : 게시글 삭제동작 관여
     */
    public void init(){

        commandMap.put("list", new BoardServiceList());
        commandMap.put("write", new BoardServiceWriteAction());
        commandMap.put("read", new BoardServiceRead());
        commandMap.put("update", new BoardServiceUpdateAction());
        commandMap.put("delete", new BoardServiceDeleteAction());
        commandMap.put("reply", new BoardServiceReplyWriteAction());
        commandMap.put("replyList", new BoardServiceReplyListAction());

    }

    /**
     * get 요청시 동작
     *
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws IOException
     * @throws ServletException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            doAction(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * post 요청시 동작
     *
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws IOException
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doAction(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 화면에서 요청되는 파라미터인 action의 값을 통해 서비스를 호출함
     * action값이 빈값이거나 null일때 list를 요청함
     * @param request
     * @param response
     * @throws Exception
     */
    private void doAction(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String action = request.getParameter("action");
        if(StringUtils.isNullOrEmpty(action)) {
            action = "list";
        }

        BoardService service = commandMap.get(action);
        service.runService(request, response);

    }

}
