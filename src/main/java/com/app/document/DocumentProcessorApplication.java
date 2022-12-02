package com.app.document;

import com.app.document.repositories.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PreDestroy;
@Slf4j
@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class DocumentProcessorApplication {

	@Autowired
	private DocumentRepository documentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DocumentProcessorApplication.class, args);
	}

	@PreDestroy
	public void onExit() {
		documentRepository.deleteAll();
		log.info("Great!");
	}
}
