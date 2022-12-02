package com.app.document.controllers;

import com.app.document.config.RepositoryPopulator;
import com.app.document.dto.DocumentDto;
import com.app.document.dto.FileDto;
import com.app.document.repositories.DocumentRepository;
import com.app.document.services.DocumentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {

    @Autowired
    private final DocumentService documentService;

    @Autowired
    private final DocumentRepository documentRepository;

    @Autowired
    private final RepositoryPopulator repositoryPopulator;

    @PostMapping("/")
    public ResponseEntity<Boolean> uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        final FileDto fileDto = new FileDto(file.getOriginalFilename(), file.getBytes());
        try {
            if (documentService.saveDocument(fileDto)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<DocumentDto>> getAllDocuments(@RequestParam(required = false) Integer page,
                                                             @RequestParam(required = false) Integer size,
                                                             @RequestParam(required = false) Sort.Direction sortDirection,
                                                             @RequestParam(required = false) String property) {

        return ResponseEntity.ok(documentService.getAllDocuments(
                page == null ? 0 : page,
                size == null ? 5 : size,
                sortDirection == null ? Sort.Direction.ASC : sortDirection,
                property == null ? "newspaperName" : property)
        );
    }

    @DeleteMapping("/")
    public ResponseEntity deleteDocuments() {
        documentRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
