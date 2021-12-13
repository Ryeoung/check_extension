package com.flow.extension.dao;

import com.flow.extension.domain.Extension;
import com.flow.extension.enums.ExtensionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtensionDao extends JpaRepository<Extension, Long> {
    /**
     * @param name 확장자 이름
     * @return Exception
     *
     * 확장자 이름으로 검색
     */
    Extension findByName(String name);


    /**
     * @param extensionId 파일 확장자 객체 기본키
     * @return Extension
     *
     *  확장자 기본키로 검색
     */
    Extension findByExtensionId(Long extensionId);


    /**
     * @param type  확장자 타입(default, custom)
     * @return int 데이터수
     *  해당 확장자 타입에 맞는 데이터 수 반환
     */
    int countByType(ExtensionType type);
}
