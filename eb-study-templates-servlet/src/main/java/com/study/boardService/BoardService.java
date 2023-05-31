package com.study.boardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BoardService {
    String runService(HttpServletRequest request, HttpServletResponse response);

}
