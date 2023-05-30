package com.study.boardControler;

import com.study.boardService.BoardServiceList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class BoardControllerServlet extends HttpServlet {
    Map commandMap = new HashMap();
    public void init(){

        commandMap.put("boardList", new BoardServiceList() );
//        commandMap.put("getCategoryList", new GetCategoryList() );
//        commandMap.put("write", new WriteService() );
//        commandMap.put("read", new ReaedService() );
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if("/".equals(uri)||"/main".equals(uri)){
            commandMap.get("boardList");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }




}
