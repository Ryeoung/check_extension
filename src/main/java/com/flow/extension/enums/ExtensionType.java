package com.flow.extension.enums;

/**
 * 확장자 타입(커스텀, 고정)
 */
public enum ExtensionType {
    DEFAULT(1),
    CUSTOM(2);

    int code;

    ExtensionType(int code) {
        this.code = code;
    }
}
