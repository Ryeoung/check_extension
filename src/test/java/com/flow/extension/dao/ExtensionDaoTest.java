package com.flow.extension.dao;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ExtendWith(SpringExtension.class)
public class ExtensionDaoTest {
    final ExtensionDao extensionDao;

    @Autowired
    public ExtensionDaoTest(ExtensionDao extensionDao) {
        this.extensionDao = extensionDao;
    }

    @Test
    public void findByName(String name) {

    }
}
