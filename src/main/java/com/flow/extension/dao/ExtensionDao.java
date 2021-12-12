package com.flow.extension.dao;

import com.flow.extension.domain.Extension;
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
}
