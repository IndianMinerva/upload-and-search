package com.app.document.services;

import com.app.document.dto.DocumentDto;
import com.app.document.dto.FileDto;
import com.app.document.entity.DocumentEntity;
import com.app.document.mapper.DocumentMapper;
import com.app.document.repositories.DocumentRepository;
import com.app.document.utils.XMLUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public boolean saveDocument(FileDto fileDto) throws Exception {
        String fileName = UUID.randomUUID().toString();
        Path path = Paths.get(fileName);
        Files.write(path, fileDto.getFileContent());
        if (XMLUtils.validate(path.toFile())) {
            //DocumentEntity documentEntity = XMLUtils.getDocumentEntity(path.toFile());
            path.toFile().delete();
            return true;
        } else {
            path.toFile().delete();
            return false;
        }
    }

    @Override
    public List<DocumentDto> getAllDocuments(int page, int size, Sort.Direction sortDirection, String property) {
        return documentRepository.findAll(PageRequest.of(page, size, Sort.by(sortDirection, property)))
                .stream()
                .map(DocumentMapper::mapDocumentEntityToDocumentDto)
                .toList();
    }
}
