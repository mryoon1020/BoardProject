package com.study.boardspringvueback.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BoardController {
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
        list.add("됫으면 좋겠다");
        return list;
    }
    @GetMapping("/api")
    public String api(){
        System.out.println("/api 들렀음");
        return "api 로 호출한거 준비중...";
    }
}
