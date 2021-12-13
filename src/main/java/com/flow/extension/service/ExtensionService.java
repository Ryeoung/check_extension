package com.flow.extension.service;

import com.flow.extension.dao.ExtensionDao;
import com.flow.extension.domain.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

/**
 * 확장자 객체에 관련된 서비스 클래스
 */
@Service
public class ExtensionService {
    final ExtensionDao extensionDao;

    @Autowired
    public ExtensionService(ExtensionDao extensionDao) {
        this.extensionDao = extensionDao;
    }

    /**
     * @param name 확장자 이름
     * @return Extension
     * 확장자 이름으로 확장자 클래스 찾기
     */
    public Extension findByName(String name) {
        return extensionDao.findByName(name);
    }

    /**
     * @return List<Extension>
     *    모든 확장자 데이터 반환하기
     */
    public List<Extension> findAll() {
        return extensionDao.findAll();
    }

    /**
     * @param extension 확장자 객체
     * @return Extension 삽입한 확장자 객체
     * 확장자 이름을 살펴보고 없으면 확장자 객체를 삽입한다.
     * 있으면 Null
     */
    public Extension insertExtension(Extension extension) {
        Extension findExtension = extensionDao.findByName(extension.getName());
        if(findExtension == null) {
            return extensionDao.save(extension);
        }

        return null;
    }

    /**
     * @param extensionId 확장자 기본키
     * @param extension 확장자 객체
     * @return Extension 수정한 확장자 객체
     * 확장자 객체를 수정한다.
     * 만약 확장짜 객체가 없으면 Null
     */
    public Extension updateExtension(Long extensionId, Extension extension) {
        Extension findExtension = extensionDao.findByExtensionId(extensionId);
        if(findExtension == null) {
            return null;
        }
        findExtension.setBlock(extension.isBlock());
        return extensionDao.save(findExtension);
    }

    /**
     * @param extensionId 파일 확장자 기본키
     * @return boolean 파일 확장자 객체를 지웠는지 체크
     *
     *  파일 확장자 데이터 지우기
     */
    public boolean deleteExtension(Long extensionId) {
        Extension findExtension = extensionDao.findByExtensionId(extensionId);
        boolean isDelete = false;
        if(findExtension != null) {
            extensionDao.delete(findExtension);
            isDelete = true;
        }
        return isDelete;
    }
}
