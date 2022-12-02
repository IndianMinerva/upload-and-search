package com.app.document.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FileDto {
    @Getter
    private final String fileName;

    @Getter
    private final byte[] fileContent;
}
