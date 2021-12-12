package com.flow.extension.dao;

import com.flow.extension.domain.Extension;
import com.flow.extension.utils.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.util.List;

/**
 * ExtensionDao 테스트
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExtensionDaoTest {

    final ExtensionDao extensionDao;

    @Autowired
    public ExtensionDaoTest(ExtensionDao extensionDao) {
        this.extensionDao = extensionDao;
    }


    /**
     * 파일 확장자 저장
     */
    @Test
    public void save() {
        Extension extension = TestUtil.getExtension();
        //when
        Extension savedExtension = extensionDao.save(extension);

        //then
        Assertions.assertEquals(extension.getName(), savedExtension.getName());
    }

    /**
     * 파일 확장자 리스트 삽입
     */
    @Test
    public void saveAll() {

        List<Extension> extensions = TestUtil.getExtensionList();

        //when
        List<Extension> savedExtensions = extensionDao.saveAll(extensions);

        //then
        Assertions.assertEquals(extensions.get(0).getName(), savedExtensions.get(0).getName());
        Assertions.assertEquals(extensions.size(), savedExtensions.size());

    }

    /**
     * 파일 확장자 이름으로 파일 확장자 데이터 찾기 테스트
     */
    @Test
    void findByName() {
        String name = "bat";
        Extension extension = TestUtil.getExtension();

        //when
        extensionDao.save(extension);
        Extension findExtension = extensionDao.findByName(name);

        //then
        Assertions.assertEquals(extension.getName(), findExtension.getName());
    }

    /**
     * 모든 파일 확장자 찾기
     */
    @Test
    void findAll() {

        List<Extension> extensions = TestUtil.getExtensionList();
        //when
        extensionDao.saveAll(extensions);

        //then
        List<Extension> findExtensions = extensionDao.findAll();
        Assertions.assertEquals(findExtensions.size(), extensions.size());

    }

    /**
     * 파일 확장자 삭제 테스트
     */
    @Test
    void delete() {
        //given
        Extension extension = TestUtil.getExtension();
        extensionDao.save(extension);

        //when
        extensionDao.delete(extension);

        //then
        Extension findExtension = extensionDao.findByName(extension.getName());
        Assertions.assertEquals(findExtension, null);

    }
}
