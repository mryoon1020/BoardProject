package com.study.util;

public class MyUtility {

    /**
     * null값이 입력되었을 때 공백으로 바꿔주는 메서드
     * @param str
     * @return String 으로 반환
     */
    public static String checkNullChangeToEmptyString(String str){

        return(str == null || str.equals("")) ? "" : str;

    }

}
