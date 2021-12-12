package com.flow.extension.domain;

import com.flow.extension.converter.ExtensionTypeConverter;
import com.flow.extension.enums.ExtensionType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "extension")
public class Extension {
    @Id
    @GeneratedValue
    @Column(name = "extension_id")
    private long extensionId;

    @Column(name = "name",length = 20, nullable = false)
    private String name;

    @Convert(converter = ExtensionTypeConverter.class)
    @Column(name = "type", nullable = false)
    private ExtensionType type;

    @Column(name = "block", nullable = false)
    private boolean isBlock;

}
