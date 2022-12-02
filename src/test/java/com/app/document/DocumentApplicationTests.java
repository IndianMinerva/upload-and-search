package com.app.document;

import com.app.document.config.EnableEmbeddedMongo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableEmbeddedMongo
class DocumentApplicationTests {
	@Test
	void contextLoads() {
	}
}
