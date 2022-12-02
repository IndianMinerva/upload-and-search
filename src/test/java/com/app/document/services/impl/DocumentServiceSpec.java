package com.app.document.services.impl;

import com.app.document.services.DocumentService;
import com.app.document.repositories.DocumentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class DocumentServiceSpec {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    DocumentService documentService;

    @Autowired
    MongoTemplate mongoTemplate;

    @AfterEach
    public void tearDown() {
        documentRepository.deleteAll();
    }
}
