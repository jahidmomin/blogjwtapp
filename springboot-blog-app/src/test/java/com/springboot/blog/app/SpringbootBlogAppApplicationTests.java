package com.springboot.blog.app;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBlogAppApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(10, 10);
	}
	
	@Test
	void test2() {
		assertEquals(true, false);
	}

}
