package biz.birque.coding.test;

import biz.birque.coding.test.one.CsvFileRead;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {
	@Autowired
	private CsvFileRead csvFileRead;

	@Test
	void 문제해결() {
		csvFileRead.csvRead();
	}

}
