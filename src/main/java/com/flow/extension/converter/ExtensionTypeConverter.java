package com.flow.extension.converter;

import com.flow.extension.enums.ExtensionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ExtensionTypeConverter implements AttributeConverter<ExtensionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ExtensionType extensionType) {
        return extensionType.getCode();
    }

    @Override
    public ExtensionType convertToEntityAttribute(Integer code) {
        return ExtensionType.ofCode(code);
    }
}
