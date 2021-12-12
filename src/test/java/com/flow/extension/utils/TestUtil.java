package com.flow.extension.utils;

import com.flow.extension.domain.Extension;
import com.flow.extension.enums.ExtensionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Junit 테스트시 필요한 임시 객체를 생성하는 클래스
 */
public class TestUtil {
    /**
     * @return Extension
     * 자주 테스트에 사용하는 Extension 객체 반환
     */

    static public Extension getExtension() {
        String name = "bat";
        Extension extension = new Extension();
        extension.setName(name);
        extension.setBlock(false);
        extension.setType(ExtensionType.DEFAULT);
        return extension;
    }

    /**
     * @return List<Extension>
     *     임시로 만든 파일 확장자 리스트 반환
     */
    static public List<Extension> getExtensionList() {
        Extension extension1 = new Extension();
        extension1.setName("bat");
        extension1.setBlock(false);
        extension1.setType(ExtensionType.DEFAULT);

        Extension extension2 = new Extension();
        extension2.setName("exc");
        extension2.setBlock(false);
        extension2.setType(ExtensionType.DEFAULT);

        List<Extension> extensions = new ArrayList<>();
        extensions.add(extension1);
        extensions.add(extension2);

        return extensions;
    }
}
