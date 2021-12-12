package com.flow.extension.domain;

import com.flow.extension.converter.ExtensionTypeConverter;
import com.flow.extension.enums.ExtensionType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "extension")
public class Extension {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "extension_id")
    private Long extensionId;

    @Column(name = "name",length = 20, nullable = false, unique = true)
    private String name;

    @Convert(converter = ExtensionTypeConverter.class)
    @Column(name = "type", nullable = false)
    private ExtensionType type;

    @Column(name = "block", nullable = false)
    private boolean isBlock;

}
