package com.study.controller;

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

    public void init(){

        commandMap.put("boardList", new BoardServiceList());
        commandMap.put("boardWriteAction", new BoardServiceWriteAction());
        commandMap.put("boardRead", new BoardServiceRead());
        commandMap.put("boardUpdate", new BoardServiceRead());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();

        if ("/board/main".equals(uri)) {

            BoardService boardService = commandMap.get("boardList");
            String result = boardService.runService(request, response);
            request.getRequestDispatcher(result).forward(request, response);

        } else if ("/board/read".equals(uri)) {

            BoardService boardService = commandMap.get("boardRead");
            String result = boardService.runService(request,response);
            request.getRequestDispatcher(result).forward(request, response);

        } else if ("/board/update".equals(uri)) {

        } else if ("/board/write".equals(uri)) {

            request.getRequestDispatcher("/WEB-INF/views/write.jsp").forward(request, response);


        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doPost(request, response);

        String uri = request.getRequestURI();

        if("/board/writeAction".equals(uri)){

            BoardService boardService = commandMap.get("boardWriteAction");
            String result = boardService.runService(request,response);
//            request.getRequestDispatcher(result).forward(request, response);
            response.sendRedirect(result);

        }

    }

}
