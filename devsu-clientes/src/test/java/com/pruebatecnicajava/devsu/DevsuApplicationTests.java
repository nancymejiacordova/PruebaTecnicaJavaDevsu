package com.pruebatecnicajava.devsu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest (classes = {DevsuApplication.class})
class DevsuApplicationTests {

	@Test
	void contextLoads() {
        DevsuApplication.main(new String[] {});
        Assertions.assertTrue(true);
	}

}
