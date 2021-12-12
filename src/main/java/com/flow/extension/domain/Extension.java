package com.flow.extension.domain;

import com.flow.extension.enums.ExtensionType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "extension")
public class Extension {
    @Id
    @GeneratedValue
    @Column(name = "extension_id")
    private long extensionId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private ExtensionType type;

    @Column(name = "block", nullable = false)
    private boolean isBlock;

}
