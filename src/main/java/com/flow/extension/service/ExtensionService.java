package com.flow.extension.service;

import com.flow.extension.dao.ExtensionDao;
import com.flow.extension.domain.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtensionService {
    final ExtensionDao extensionDao;

    @Autowired
    public ExtensionService(ExtensionDao extensionDao) {
        this.extensionDao = extensionDao;
    }

    public Extension findByName(String name) {
        return extensionDao.findByName(name);
    }

    public void insertExtension(Extension extension) {
        extensionDao.save(extension);
    }
}
