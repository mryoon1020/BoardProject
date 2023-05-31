package com.study.boardControler;

import com.study.boardService.BoardService;
import com.study.boardService.BoardServiceList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/board/*")
public class BoardControllerServlet extends HttpServlet {
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
            request.getRequestDispatcher(result).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doPost(request, response);
    }

    public void destroy() {
    }
}
