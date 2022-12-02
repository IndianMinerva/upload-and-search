package com.app.document;

import com.app.document.config.RepositoryPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class DocumentProcessorApplication {

	@Autowired
	private RepositoryPopulator repositoryPopulator;

	public static void main(String[] args) {
		SpringApplication.run(DocumentProcessorApplication.class, args);
	}

}
