package com.study.controller;

import com.study.service.BoardService;
import com.study.service.BoardServiceList;

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

    public void init(){
        System.out.println("init running");
        commandMap.put("boardList", new BoardServiceList());
        // commandMap.put("getCategoryList", new GetCategoryList());
        // commandMap.put("write", new WriteService());
        // commandMap.put("read", new ReaedService());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        System.out.println(uri);
        if ("/board/main".equals(uri)) {
            BoardService boardService = commandMap.get("boardList");
           String result = boardService.runService(request, response);
//            System.out.println(result);
            request.getRequestDispatcher(result).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doPost(request, response);
    }

}
