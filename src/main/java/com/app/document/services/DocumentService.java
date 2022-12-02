package com.app.document.services;

import com.app.document.dto.DocumentDto;
import com.app.document.dto.FileDto;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    boolean saveDocument(FileDto fileDto) throws Exception;
    List<DocumentDto> getAllDocuments(int page, int size, Sort.Direction sortDirection, String property);
}
