package com.flow.extension.service.service;

import com.flow.extension.dao.ExtensionDao;
import com.flow.extension.domain.Extension;
import com.flow.extension.enums.ExtensionType;
import com.flow.extension.service.ExtensionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class ExtensionServiceTest {
    @InjectMocks
    ExtensionService extensionService;

    @Mock
    ExtensionDao extensionDao;


    /**
     * 확장자 삽입 테스트
     */
    @Test
    void insert() {
        Extension extension = new Extension();
        extension.setName("bat");
        extension.setBlock(false);
        extension.setType(ExtensionType.DEFAULT);
        given(extensionDao.save(extension)).willReturn(extension);

        //when
        Extension inserted =  extensionService.insertExtension(extension);

        //then
        Assertions.assertEquals(extension.getName(), inserted.getName());


    }

    /**
     * 수정 테스트
     */
    @Test
    void update() {
        Extension extension = new Extension();
        extension.setName("bat");
        extension.setBlock(false);
        extension.setType(ExtensionType.DEFAULT);
        given(extensionDao.save(extension)).willReturn(extension);

        //when
        Extension inserted =  extensionService.updateExtension(extension);

        //then
        Assertions.assertEquals(extension.getName(), inserted.getName());


    }

    /**
     * 확장자 명으로 확장자 객체 찾기.
     */
    @Test
    void findByName() {
        String name = "bat";
        Extension extension = new Extension();
        extension.setName(name);
        extension.setBlock(false);
        extension.setType(ExtensionType.DEFAULT);

        given(extensionDao.findByName(name)).willReturn(extension);

        //when
        Extension findExtension = extensionService.findByName(name);

        //then
        Assertions.assertEquals(extension.getName(), findExtension.getName());
    }
}
