package com.app.document.mapper;

import com.app.document.dto.DocumentDto;
import com.app.document.entity.DocumentEntity;
import org.springframework.stereotype.Component;

public class DocumentMapper {
    public static DocumentDto mapDocumentEntityToDocumentDto(DocumentEntity documentEntity) {
        return new DocumentDto(
                documentEntity.getId(),
                documentEntity.getNewspaperName(),
                documentEntity.getWidth(),
                documentEntity.getHeight(),
                documentEntity.getDpi(),
                documentEntity.getFilename(),
                documentEntity.getPublicationDate(),
                documentEntity.getUploadTime()
        );
    }
}
