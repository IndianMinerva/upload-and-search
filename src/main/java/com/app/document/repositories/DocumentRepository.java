package com.app.document.repositories;

import com.app.document.entity.DocumentEntity;
import org.junit.runner.manipulation.Sortable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<DocumentEntity, String> {
}
