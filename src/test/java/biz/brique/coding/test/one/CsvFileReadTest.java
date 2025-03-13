package biz.brique.coding.test.one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvFileReadTest {

    @Autowired
    private CsvFileRead csvFileRead;

    private String downloadUrl;

    /* 요청할 url 설정 */
    @BeforeEach
    void setUp() {
        String FILE_ID = "1Ah0gkauGCIqJHpFGhTgsEZCjYFRscjTh";
        downloadUrl= "https://drive.google.com/uc?export=download&id=" + FILE_ID;
    }

    @Test
    void CSV_파일읽기() {
        csvFileRead.csvRead(downloadUrl);
    }
}