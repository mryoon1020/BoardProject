package com.study.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
    void runService(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
