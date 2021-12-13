package com.flow.extension.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ResponseStatus {
    SUCCESS("success", "성공"),
    EXTENSION_NOT_FOUND("fail01", "해당 확장자를 찾지 못했습니다."),
    EXTENSION_EXISIT("fail02", "해당 확장자는 이미 존재합니다."),
    DB_ACCESS_FAIL_ERROR("fail03","DB 관련된 에러가 발생하였습니다."),
    NO_VALUE_OF_EXTENSION_TYPE("fail04", "파일 확장자 타입을 찾을 수 없습니다."),
    MAX_DATA_OF_CUSTOM_ERROR("fail05", "커스텀 파일 확장자 데이터가 200개 넘었습니다."),
    INTERNAL_SERVER_ERROR("fail06","내부 서버 에러가 발생했습니다.");

    String code;
    String msg;

    ResponseStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
