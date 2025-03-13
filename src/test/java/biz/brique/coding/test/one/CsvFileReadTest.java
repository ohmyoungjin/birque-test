package biz.brique.coding.test.one;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvFileReadTest {

    @Autowired
    private CsvFileRead csvFileRead;

    @Test
    void CSV_파일읽기() {
        csvFileRead.csvRead();
    }
}