package com.flow.extension.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ResponseStatus {
    SUCCESS("success", "성공"),
    EXTENSION_NOT_FOUND("fail01", "해당 확장자를 찾지 못했습니다."),
    EXTENSION_EXISIT("fail02", "해당 확장자는 이미 존재합니다.");
    String code;
    String msg;

    ResponseStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
