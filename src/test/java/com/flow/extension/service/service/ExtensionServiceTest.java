package com.flow.extension.service.service;

import com.flow.extension.dao.ExtensionDao;
import com.flow.extension.domain.Extension;
import com.flow.extension.enums.ExtensionType;
import com.flow.extension.service.ExtensionService;
import com.flow.extension.utils.TestUtil;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ExtensionServiceTest {
    @InjectMocks
    ExtensionService extensionService;

    @Mock
    ExtensionDao extensionDao;


    /**
     * 파일 확장자 성공 테스트
     */
    @Test
    void insert() {
        String name = "bat";
        Extension extension = TestUtil.getExtension();
        extension.setType(ExtensionType.DEFAULT);
        given(extensionDao.findByName(name)).willReturn(null);
        given(extensionDao.save(extension)).willReturn(extension);

        //when
        Extension inserted =  extensionService.insertExtension(extension);
        //then
        Assertions.assertEquals(inserted, inserted);
    }

    /**
     *  파일 확장자 삽입 실패 테스트
     */
    @Test
    void insertToFail() {
        String name = "bat";
        Extension extension = TestUtil.getExtension();
        given(extensionDao.findByName(name)).willReturn(extension);

        //when
        Extension inserted =  extensionService.insertExtension(extension);

        //then
        Assertions.assertEquals(inserted, null);

    }

    /**
     * 수정 성공 테스트
     */
    @Test
    void update() {
        String name = "bat";
        Extension extension = TestUtil.getExtension();
        extension.setExtensionId(0L);
        given(extensionDao.findByExtensionId(0L)).willReturn(extension);
        given(extensionDao.save(extension)).willReturn(extension);


        //when
        Extension updated =  extensionService.updateExtension(0L, extension);

        //then
        Assertions.assertEquals(extension.getName(), updated.getName());


    }

    /**
     * 수정 실패 테스트
     */
    @Test
    void updateToFail() {
        String name = "bat";
        Extension extension = TestUtil.getExtension();
        extension.setType(ExtensionType.DEFAULT);
        extension.setExtensionId(0L);
        given(extensionDao.findByExtensionId(0L)).willReturn(null);


        //when
        Extension updated =  extensionService.updateExtension(0L, extension);

        //then
        Assertions.assertEquals(updated, null);
    }

    /**
     * 확장자 명으로 확장자 객체 찾기.
     */
    @Test
    void findByName() {
        String name = "bat";
        Extension extension = TestUtil.getExtension();

        given(extensionDao.findByName(name)).willReturn(extension);

        //when
        Extension findExtension = extensionService.findByName(name);

        //then
        Assertions.assertEquals(extension.getName(), findExtension.getName());
    }

    /**
     * 모든 파일 확장자 데이터 가져오기
     */
    @Test
    void findAll() {
        List<Extension> extensions = TestUtil.getExtensionList();

        given(extensionDao.findAll()).willReturn(extensions);

        //when
        List<Extension> findExtensions = extensionService.findAll();

        //then
        Assertions.assertEquals(extensions.size(), findExtensions.size());
    }

    /**
     * 파일 확장자 기본키로 데이터 지우기 확인
     */
    @Test
    void deleteByExtensionId() {
        Extension extension = TestUtil.getExtension();
        extension.setExtensionId(0L);
        given(extensionDao.findByExtensionId(0L)).willReturn(extension);
        //when
        boolean isDelete = extensionService.deleteExtension(0L);

        //then
        verify(extensionDao, times(1)).delete(extension);
        Assertions.assertEquals(isDelete, true);
    }

    /**
     *  파일 확장자 지우기 실패
     */
    @Test
    void deleteByExtensionIdFail() {
        Extension extension = TestUtil.getExtension();
        extension.setExtensionId(0L);
        given(extensionDao.findByExtensionId(0L)).willReturn(null);
        //when
        boolean isDelete = extensionService.deleteExtension(0L);

        //then
        verify(extensionDao, times(0)).delete(extension);
        Assertions.assertEquals(isDelete, false);
    }
}
