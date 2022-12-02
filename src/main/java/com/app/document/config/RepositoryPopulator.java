package com.app.document.config;

import com.app.document.entity.DocumentEntity;
import com.app.document.repositories.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Configuration
public class RepositoryPopulator {
    @Autowired
    private DocumentRepository documentRepository;
    @Bean
    @Autowired
    public RepositoryPopulator loadData() {
        documentRepository.insert(new DocumentEntity(null, "newspaper1", 10.1d, 20.1d, 30.1d, "fileName1", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper2", 10.2d, 20.2d, 30.1d, "fileName2", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper3", 10.3d, 20.3d, 30.1d, "fileName3", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper4", 10.4d, 20.4d, 30.1d, "fileName4", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper5", 10.5d, 20.5d, 30.1d, "fileName5", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper6", 10.6d, 20.6d, 30.1d, "fileName6", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper7", 10.7d, 20.7d, 30.1d, "fileName7", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper8", 10.8d, 20.8d, 30.1d, "fileName8", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper9", 10.9d, 20.9d, 30.1d, "fileName9", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper10", 10.10d, 20.10d, 30.1d, "fileName10", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper11", 10.11d, 20.11d, 30.1d, "fileName11", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper12", 10.12d, 20.12d, 30.1d, "fileName12", new Date(), LocalDateTime.now()));
        documentRepository.insert(new DocumentEntity(null, "newspaper13", 10.13d, 20.13d, 30.1d, "fileName13", new Date(), LocalDateTime.now()));
        log.info("Populated");
        return this;
    }
}
