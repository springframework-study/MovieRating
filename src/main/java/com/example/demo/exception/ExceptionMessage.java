package com.example.demo.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    NAVER_API_UNAUTHORIZED("네이버 오픈 API통신 중 인증 에러가 발생하였습니다."),
    NAVER_API_BAD_REQUEST("잘못된 요청 구문, 또는 유효하지 않는 요청입니다."),
    NAVER_API_ERROR("네이버 모든 API 통신  알 수 없는 에러가 발생하였습니다."),
    NO_CONTENT("데이터가 없습니다.");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
