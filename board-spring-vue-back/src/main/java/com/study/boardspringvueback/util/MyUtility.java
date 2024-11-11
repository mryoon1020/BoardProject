package com.study.boardspringvueback.util;

import com.study.boardspringvueback.vo.BoardPageSearchVO;

public class MyUtility {

    /**
     * null값이 입력되었을 때 공백으로 바꿔주는 메서드
     * @param str
     * @return String 으로 반환
     */
    public static String checkNullChangeToEmptyString(String str){

        return (str == null || str.equals("")) ? "" : str;

    }

    /**
     * vo의 null 을 확인후 공백문자로 바꿔주는 메서드
     * @param vo
     * @return null이 제거된 vo 반환
     */
    public static BoardPageSearchVO checkNullChangeToEmptyString(BoardPageSearchVO vo) {
        String boardCategoryNo = checkNullChangeToEmptyString(vo.getBoardCategoryNo());
        String keyWord = checkNullChangeToEmptyString(vo.getSearchKeyWord());
        String startDate = checkNullChangeToEmptyString(vo.getStartDate());
        String endDate = checkNullChangeToEmptyString(vo.getEndDate());

        vo.setBoardCategoryNo(boardCategoryNo);
        vo.setSearchKeyWord(keyWord);
        vo.setStartDate(startDate);
        vo.setEndDate(endDate);

        return vo;
    }

    /**
     * null과 공백을 체크하여 true 또는 false를 반환하는 메서드 입니다.
     * @param str
     * @return boolean
     */
    public static boolean checkNullEmptyString (String str) {
        return str == null || str.isEmpty();
    }
}
