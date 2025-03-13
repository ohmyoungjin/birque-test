package biz.brique.coding.test.three.repository;

import biz.brique.coding.test.config.TestJpaConfig;
import biz.brique.coding.test.dto.QueryResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestJpaConfig.class)
class QueryRepositoryQueryDslTest {

    @Autowired
    private QueryRepositoryQueryDsl queryRepositoryQueryDsl;

    @Test
    void 쿼리조회() {
        List<QueryResponseDto> queryResponseDtoList = queryRepositoryQueryDsl.problemThree();
        queryResponseDtoList.forEach(System.out::println);
        System.out.println(queryResponseDtoList.size() + " rows in set");

    }
}
