package com.flow.extension.enums;

import com.flow.extension.exceptions.NoValueOfExtensionTypeException;
import lombok.Getter;

import java.util.Arrays;

/**
 * 확장자 타입(커스텀, 기본)
 */

@Getter
public enum ExtensionType {
    DEFAULT("기본", 1),
    CUSTOM("사용자지정", 2);

    private String desc;
    private int code;
    ExtensionType(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * @param code DB에 저장되는 코드 번호
     * @return ExtensionType
     *
     *  코드 번호에 해당하는 ExtensionType 찾기
     *  만약 못 찾으면 NoValueOfExtensionTypeException 발생
     */
    public static ExtensionType ofCode(int code) {
        return Arrays.stream(ExtensionType.values())
                .filter(v -> v.getCode() == code)
                .findAny()
                .orElseThrow(() -> new NoValueOfExtensionTypeException(
                        String.format("상태 코드(ExtensionType)에 code = [%d]가 존재하지 않습니다.", code)
                ));
    }
}
