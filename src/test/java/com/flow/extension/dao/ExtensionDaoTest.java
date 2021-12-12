package com.flow.extension.dao;

import com.flow.extension.domain.Extension;
import com.flow.extension.enums.ExtensionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExtensionDaoTest {

    final ExtensionDao extensionDao;

    @Autowired
    public ExtensionDaoTest(ExtensionDao extensionDao) {
        this.extensionDao = extensionDao;
    }


    @Test
    public void save() {
        Extension extension = new Extension();
        extension.setName("bat");
        extension.setBlock(false);
        extension.setType(ExtensionType.DEFAULT);

        Extension savedExtension = extensionDao.save(extension);
        Assertions.assertEquals(extension.getName(), savedExtension.getName());
    }

    @Test
    public void saveAll() {
        Extension extension1 = new Extension();
        extension1.setName("bat");
        extension1.setBlock(false);
        extension1.setType(ExtensionType.DEFAULT);

        Extension extension2 = new Extension();
        extension2.setName("bat");
        extension2.setBlock(false);
        extension2.setType(ExtensionType.DEFAULT);

        List<Extension> extensions = new ArrayList<>();
        extensions.add(extension1);
        extensions.add(extension2);

        List<Extension> savedExtensions = extensionDao.saveAll(extensions);
        System.out.println(extensions.get(0));
        System.out.println(savedExtensions.get(0));
        Assertions.assertEquals(extensions.get(0).getName(), savedExtensions.get(0).getName());
    }

    /**
     * 파일 확장자 이름으로 파일 확장자 데이터 찾기 테스트
     */
    @Test
    void findByName() {
        String name = "bat";
        Extension extension = new Extension();

        extension.setName(name);
        extension.setBlock(false);
        extension.setType(ExtensionType.DEFAULT);

        extensionDao.save(extension);
        Extension findExtension = extensionDao.findByName(name);

        Assertions.assertEquals(extension.getName(), findExtension.getName());
    }
}
